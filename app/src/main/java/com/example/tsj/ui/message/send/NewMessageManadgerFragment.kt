package com.example.tsj.ui.message.send


import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.R
import com.example.tsj.ui.message.MessagesViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.new_message_chairman.*
import okhttp3.MultipartBody
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.adapters.message.ManagerAdapter
import com.example.tsj.adapters.message.GeneralClickListener
import java.io.File
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class NewMessageManadgerFragment : Fragment(), GeneralClickListener {

    private lateinit var viewModel: MessagesViewModel
    private lateinit var editBody: TextInputEditText
    private lateinit var editTitle: TextInputEditText
    private lateinit var managerAdapter: ManagerAdapter
    private lateinit var recyclerManager: RecyclerView
    private val IMAGE_PICK_CODE = 10
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

        editBody = root.findViewById(R.id.manager_msg_referenc)
        editTitle = root.findViewById(R.id.manager_msg_content)

        recyclerManager = root.findViewById(R.id.recycler_view_manager)
        getRecyclerView()

        return root
    }

    override fun onClickManager(position: Int) {
        names.removeAt(position)
        files.removeAt(position)
        managerAdapter.update(names)
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
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.setType("*/*")
        startActivityForResult(myFile, IMAGE_PICK_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)

                files.add(photo)
                names.add(photo.toString().substring(0, 15))
                managerAdapter.update(names)
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


    private fun validateEditText(title: String, content: String): Boolean {
        return title.isNotEmpty() || content.isNotEmpty()
    }

    private fun sendMessage() {

        val title = manager_msg_referenc.text.toString()
        val body = manager_msg_content.text.toString()
        //проверка на пустоту edit text
        if (validateEditText(title, body)) {
            title_container.error = null
            content_container.error = null
            //Метод для post сообщания
            viewModel.sendMessageToManager(body, title, files).observe(this, Observer {
                if (it) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "Неудочно", Toast.LENGTH_LONG).show()
                }
            })

        } else {
            title_container.error = "Загаловка не может быть пустым"
            content_container.error = "Письмо не может быть пустым"
        }
    }

    private fun getRecyclerView() {
        managerAdapter = ManagerAdapter(this)
        recyclerManager.apply {
            adapter = managerAdapter

        }
    }
}
