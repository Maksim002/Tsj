package com.timelysoft.tsjdomcom.ui.payment

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aditya.filebrowser.Constants
import com.aditya.filebrowser.FileChooser
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import kotlinx.android.synthetic.main.fragment_template.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


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
        initRequest()
    }

    private fun initArgument() {
        template_choose.setOnClickListener {
            loadFiles()
        }
    }

    private fun initRequest() {
        template_approve_template.setOnClickListener {
            viewModel.paymentDownloadSave(files).observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                when (result.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
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
       val i2 = Intent(context, FileChooser::class.java)
       i2.putExtra(Constants.SELECTION_MODE, Constants.SELECTION_MODES.SINGLE_SELECTION)
       i2.putExtra(Constants.ALLOWED_FILE_EXTENSIONS, "xls;xlsx");
       startActivityForResult(i2, FILE_PICK_CODE)
   }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val path = data?.data
            if (path != null) {
                val file = File(path.path!!)
                val requestFile = file.asRequestBody("*/*".toMediaTypeOrNull())
                val myfile = MultipartBody.Part.createFormData("File", file.name, requestFile)
                files.add(myfile)
            }
        }
    }

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
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}