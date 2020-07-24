package com.timelysoft.tsjdomcom.ui.news

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.news.AddNewsModel
import kotlinx.android.synthetic.main.fragment_add_news_bottom_shit.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AddNewsBottomShitFragment : BottomSheetDialogFragment() {
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_news_bottom_shit, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
    }

    private fun initArgument() {
        shit_choose_file.setOnClickListener {
            loadFiles()
        }
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
        val myFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
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
            //permission from popup denied
            Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val list: ArrayList<AddNewsModel> = arrayListOf()

            if (data != null) {
                val uri = data.data!!
                val file = File(uri.toString())
                val requestFile = file.asRequestBody("file/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
                files.add(photo)
                names.add(photo.toString().substring(0, 15))
                val filename = file.name
                list.add(AddNewsModel(0,filename))

                val bundle = Bundle()
                bundle.putSerializable("file", filename)
                findNavController().navigate(R.id.navigation_add_news, bundle)
                dismiss()
            }
        }
    }
}