package com.timelysoft.tsjdomcom.ui.message.send


import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.ui.message.MessagesViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.new_message_chairman.*
import okhttp3.MultipartBody
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.adapters.files.FilesAdapter
import com.timelysoft.tsjdomcom.adapters.files.FilesModel
import com.timelysoft.tsjdomcom.adapters.files.GeneralClickListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.new_message_chairman.view.*
import java.io.File
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull


class NewMessageMandgerFragment : Fragment(), GeneralClickListener {

    private lateinit var viewModel: MessagesViewModel
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private lateinit var editBody: TextInputEditText
    private lateinit var editTitle: TextInputEditText
    private lateinit var filesAdapter: FilesAdapter
    private lateinit var recyclerManager: RecyclerView

    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.new_message_chairman, container, false)

        editBody = root.findViewById(R.id.manager_message_ref)
        editTitle = root.findViewById(R.id.manager_message_content)

        recyclerManager = root.findViewById(R.id.manager_recycler_сhairman)
        getRecyclerView()

        root.manager_message_ref.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.manager_message_ref.text!!.isNotEmpty()) {
                title_container_сhairman.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }

        root.manager_message_content.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.manager_message_content.text!!.isNotEmpty()) {
                content_container_сhairman.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
        return root
    }

    override fun onClickItem(position: Int, url: String, fileName: String) {
        names.removeAt(position)
        files.removeAt(position)
        val items = names.map {
            FilesModel(it)
        }
        filesAdapter.update(items)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_message -> {
                sendMessage()
            }
            R.id.fasten_file -> {
                loadFiles()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFiles() {
        MyUtils.hideKeyboard(activity!!, view!!)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                requestPermissions(permissions, STORAGE_PERMISION_CODE)

            } else {
                getMyFile()
            }
        } else {
            getMyFile()
        }
    }

    private fun getMyFile() {
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.setType("*/*")
        startActivityForResult(myFile, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getMyFile()
        } else {
            //permission from popup denied
            Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
                val filename = file.name
                files.add(photo)
                names.add(photo.toString().substring(0, 15))
                val items = names.map {
                    FilesModel(filename, "")
                }
                filesAdapter.update(items)
            }
        }
    }

    private fun getPath(uri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(context!!, uri, proj, null, null, null)
        val cursor = loader.loadInBackground()
        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val res = cursor.getString(columnIndex)
        cursor.close()
        return res

    }

    private fun sendMessage() {
        MyUtils.hideKeyboard(activity!!, view!!)
        if (validate()) {
            val title = manager_message_ref.text.toString()
            val body = manager_message_content.text.toString()
            MainActivity.alert.show()

            viewModel.sendMessageToManagerN(body, title, files).observe(this, Observer { result ->
                val msg = result.msg
                MainActivity.alert.hide()
                when(result.status){
                    Status.SUCCESS ->{
                        findNavController().popBackStack()
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR, Status.NETWORK ->{
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (manager_message_ref.getText().toString().length == 0) {
            title_container_сhairman.setError("Заголовок не должен быть пустым")
            valid = false
        } else {
            title_container_сhairman.setErrorEnabled(false)
        }

        if (manager_message_content.getText().toString().length == 0) {
            content_container_сhairman.setError("Письмо не должно быть пустым")
            valid = false
        } else {
            content_container_сhairman.setErrorEnabled(false)
        }

        return valid
    }

    private fun getRecyclerView() {
        filesAdapter = FilesAdapter(this)
        recyclerManager.apply {
            adapter = filesAdapter

        }
    }

    override fun onStart() {
        super.onStart()

        manager_message_ref.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && manager_message_ref.text!!.isNotEmpty()) {
                title_container_сhairman.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                title_container_сhairman.isErrorEnabled = false

            }

            manager_message_content.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus && manager_message_content.text!!.isNotEmpty()) {
                    content_container_сhairman.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    content_container_сhairman.isErrorEnabled = false
                }
            }
        }
    }
}
