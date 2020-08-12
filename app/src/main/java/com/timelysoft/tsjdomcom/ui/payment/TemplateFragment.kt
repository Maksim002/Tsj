package com.timelysoft.tsjdomcom.ui.payment

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nbsp.materialfilepicker.MaterialFilePicker
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import kotlinx.android.synthetic.main.fragment_template.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.regex.Pattern


class TemplateFragment : Fragment() {
    private var viewModel = PaymentViewModel()
    private val STORAGE_PERMISION_CODE: Int = 1000
    private var url: String = ""
    private val FILE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
    }

    private fun initArgument() {
        template_choose.setOnClickListener {
            loadFiles()
        }

        template_approve_template.setOnClickListener {
            viewModel.paymentDownloadSave(files).observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                when (result.status) {
                    Status.SUCCESS -> {
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

        template_download.setOnClickListener {
            viewModel.paymentDownloadTemplate().observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        downloadTemplate(data!!)
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
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
        val myFile = Intent(Intent.ACTION_GET_CONTENT)
        myFile.setType("*/*")
        startActivityForResult(Intent.createChooser(myFile, "Select Picture"), FILE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == FILE_PICK_CODE) {
            // todo работа excel  uri to moltipad bodi
            if (data != null) {
                val uri: Uri = data.data!!
                val file = File(uri.toString())
                val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), data.toString())
                val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestFile)
                files.add(multipartBody)
            }
        }
    }

//    fun getFilePath(context: Context, uri: Uri): String? {
//        var tempUri = uri
//        var selection: String? = null
//        var selectionArgs: Array<String>? = null
//
//        val projection = arrayOf(
//            MediaStore.Files.FileColumns.DATA,
//            MediaStore.Files.FileColumns.TITLE,
//            MediaStore.Files.FileColumns.MIME_TYPE
//        )
//        // Uri is different in versions after KITKAT (Android 4.4), we need to
//        when {
//            isExternalStorageDocument(tempUri) -> {
//                val docId = DocumentsContract.getDocumentId(tempUri)
//                val split = docId.split(":").toTypedArray()
//                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
//            }
//            isDownloadsDocument(tempUri) -> {
//                val id = DocumentsContract.getDocumentId(tempUri)
//                tempUri = ContentUris.withAppendedId(
//                    Uri.parse("content://downloads/public_downloads"),
//                    java.lang.Long.valueOf(id)
//                )
//            }
//            isMediaDocument(tempUri) -> {
//                val docId = DocumentsContract.getDocumentId(tempUri)
//                val split = docId.split(":").toTypedArray()
//                when (split[0]) {
//                    "image" -> {
//                        tempUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    }
//                    "video" -> {
//                        tempUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    }
//                    "audio" -> {
//                        tempUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                    }
//                }
//                selection = "_id=?"
//                selectionArgs = arrayOf(
//                    split[1]
//                )
//            }
//        }
//        if ("content".equals(tempUri.scheme, ignoreCase = true)) {
//            if (isGooglePhotosUri(tempUri)) {
//                return tempUri.lastPathSegment
//            }
//            var cursor: Cursor? = null
//            try {
//                cursor = context.contentResolver
//                    .query(tempUri, projection, selection, selectionArgs, null)
//                val columnIndex: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED + " desc")
//                if (cursor.moveToFirst()) {
//                    return cursor.getString(columnIndex)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        } else if ("file".equals(tempUri.scheme, ignoreCase = true)) {
//            return tempUri.path
//        }
//        return null
//    }
//
//    private fun isExternalStorageDocument(uri: Uri): Boolean {
//        return "com.android.externalstorage.documents" == uri.authority
//    }
//
//    private fun isDownloadsDocument(uri: Uri): Boolean {
//        return "com.android.providers.downloads.documents" == uri.authority
//    }
//
//    private fun isMediaDocument(uri: Uri): Boolean {
//        return "com.android.providers.media.documents" == uri.authority
//    }
//
//    private fun isGooglePhotosUri(uri: Uri): Boolean {
//        return "com.google.android.apps.photos.content" == uri.authority
//    }


        private fun downloadTemplate(url: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                //permission denied
                val permissions = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                //show popup to request runtime permission
                requestPermissions(permissions, STORAGE_PERMISION_CODE)
            } else {
                //permission already granted
                downloadFile(url)
            }
        } else {
            //system OS is < Marshmallow
            //pickImageFromGallery()
            downloadFile(url)
        }
    }

    private fun downloadFile(downloadUrl: String) {
        url = downloadUrl
        Toast.makeText(context, "Файл загружается...", Toast.LENGTH_LONG).show()
        val reguest = DownloadManager.Request(Uri.parse(downloadUrl))
        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        reguest.setTitle("file")
        reguest.setDescription("Файл загружается...")

        reguest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        reguest.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "${System.currentTimeMillis()}"
        )

        val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(reguest)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            STORAGE_PERMISION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadFile(url)
                    getMyFile()
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}