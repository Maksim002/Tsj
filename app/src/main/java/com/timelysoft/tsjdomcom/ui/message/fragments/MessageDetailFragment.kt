package com.timelysoft.tsjdomcom.ui.message.fragments


import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.files.FilesAdapter
import com.timelysoft.tsjdomcom.adapters.files.FilesModel
import com.timelysoft.tsjdomcom.adapters.files.GeneralClickListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.ui.message.MessagesViewModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_message_detail.*
import java.lang.Exception

class MessageDetailFragment : Fragment(), GeneralClickListener {

    private var downloadUrl = ""
    private var idMessage = 0
    private val STORAGE_PERMISION_CODE: Int = 1000
    private lateinit var viewModel: MessagesViewModel
    private lateinit var filesAdapter: FilesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_message_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initViews()
        initData()
    }

    private fun initData() {
        MainActivity.alert.show()
        viewModel.message(idMessage).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    message_detail_date.text = "Дата: "+MyUtils.toMyDate(data!!.sendDate)
                    message_detail_sender.text = data.personNameHeader + ": " + data!!.personName
                    message_detail_title.text = data.title
                    message_detail_content.text = data.body

                    //в отправленных адресов нету, провераяю для входящих
                    if (data.address != null) {
                        message_detail_address.visibility = View.VISIBLE
                        message_detail_address.text ="Адрес: ${data!!.address}"
                    } else {
                        message_detail_address.visibility = View.GONE
                    }

                    filesAdapter = FilesAdapter(this)
                    val items = data!!.attachments!!.map { attachment ->
                        FilesModel(attachment.fileName, attachment.filePath)
                    }
                    filesAdapter.update(items)
                    message_files_rv.adapter = filesAdapter
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initArguments() {
        idMessage = try {
            arguments!!.getInt("id")
        } catch (e: Exception) {
            0
        }

    }

    private fun initViews() {
        message_answer_button.setOnClickListener {
            val messageBottomSheet = MessageBottomSheet(idMessage)
            messageBottomSheet.show(fragmentManager!!, "MessageBottomSheet")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_message -> {
                deleteMessage()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteMessage() {
        MainActivity.alert.show()
       viewModel.deleteMessage(idMessage).observe(this, Observer { result ->
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

    override fun onClickItem(position: Int, url: String,fileName:String) {
        downloadUrl = url
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
        Toast.makeText(context, "Файл загружается.....", Toast.LENGTH_LONG).show()
        val reguest = DownloadManager.Request(Uri.parse(downloadUrl))
        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        reguest.setTitle(MyUtils.fileName(downloadUrl))
        reguest.setDescription("Файл загружается.....")

        reguest.allowScanningByMediaScanner()
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
                    downloadFile(downloadUrl)
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
