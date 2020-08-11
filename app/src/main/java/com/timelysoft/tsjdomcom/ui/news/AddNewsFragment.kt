package com.timelysoft.tsjdomcom.ui.news

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.files.FilesModel
import com.timelysoft.tsjdomcom.adapters.news.AddNewsAdapter
import com.timelysoft.tsjdomcom.adapters.news.AddNewsListener
import com.timelysoft.tsjdomcom.adapters.news.AddNewsModel
import com.timelysoft.tsjdomcom.adapters.news.NewsAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.news.NewsModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_add_news.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class AddNewsFragment : Fragment(), AddNewsListener {
    private var viewModel = NewsViewModel()
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()

    private var myAdapter = AddNewsAdapter(this)
    var list: ArrayList<AddNewsModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        iniArgument()
        initRecycler()
    }

    private fun iniArgument() {

    }

    private fun initRecycler() {
        myAdapter.update(list)
        add_news_recycler.adapter = myAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_news_chairman, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun addNewsClick(position: Int, fileName: String) {
        names.removeAt(position)
        files.removeAt(position)
        val items = names.map {
            AddNewsModel(position, it)
        }
        myAdapter.update(items as ArrayList<AddNewsModel>)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_message -> {
                MainActivity.alert.show()
                viewModel.newsAddMessage(files, add_news_title_out.text.toString(), add_news_content_out.text.toString()).observe(this, Observer { result->
                    val msg = result.msg
                    MainActivity.alert.hide()
                    when(result.status){
                        Status.SUCCESS ->{
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            findNavController().popBackStack()
                            MainActivity.alert.hide()
                        }
                        Status.ERROR, Status.NETWORK ->{
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
            R.id.fasten_file -> {
                loadFiles()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFiles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
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
        myFile.setType("*/*");
        startActivityForResult(Intent.createChooser(myFile,"Select Picture") , IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getMyFile()
        } else {
            Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("file/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
                files.add(photo)
                names.add(photo.toString())
                val filename = file.name
                list.add(AddNewsModel(0, filename.toString()))
                myAdapter.notifyDataSetChanged()
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