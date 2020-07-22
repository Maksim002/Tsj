package com.timelysoft.tsjdomcom.ui.news

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.loader.content.CursorLoader
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.news.AddNewsAdapter
import com.timelysoft.tsjdomcom.adapters.news.AddNewsListener
import com.timelysoft.tsjdomcom.adapters.news.AddNewsModel
import kotlinx.android.synthetic.main.fragment_add_news.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AddNewsFragment : Fragment(), AddNewsListener {
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()

    private var myAdapter = AddNewsAdapter(this)
    val list: ArrayList<AddNewsModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        iniArgument()
    }

    override fun onStart() {
        super.onStart()
        if (myAdapter.itemCount == 0) {
            add_news_add_file.visibility = View.VISIBLE
            add_news_recycler.visibility = View.GONE
        } else {
            add_news_add_file.visibility = View.GONE
            add_news_recycler.visibility = View.VISIBLE
        }
    }

    private fun iniArgument() {
        add_news_add_file.setOnClickListener {
            loadFiles()
            myAdapter.update(list)
        }
    }

    private fun initRecycler() {
        add_news_recycler.adapter = myAdapter
    }

    override fun AddNewsOnClickListener(item: AddNewsModel) {
        loadFiles()
        myAdapter.update(list)
    }

    private fun loadFiles() {

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
        myFile.type = "*/*"
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
                val photo = MultipartBody.Part.createFormData("file", file.name, requestFile)
                files.add(photo)
                names.add(photo.toString().substring(0, 15))
                list.add(AddNewsModel(uri.toString()))
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
}