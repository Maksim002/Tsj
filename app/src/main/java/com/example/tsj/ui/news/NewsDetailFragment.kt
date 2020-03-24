package com.example.tsj.ui.news


import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.adapters.files.GeneralClickListener
import com.example.tsj.adapters.news.NewsCommentAdapter
import com.example.tsj.adapters.news.NewsFilesAdapter
import com.example.tsj.service.request.NewsCommentRequest
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_news_detail.*
import java.lang.Exception

class NewsDetailFragment : Fragment(), GeneralClickListener {

    private var filePath = " "
    private val STORAGE_PERMISION_CODE: Int = 1000

    private lateinit var viewModel: NewsViewModel
    private var newsId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initViews()
        initPostComment()
    }

    private fun initPostComment() {
        news_detail_send_btn.setOnClickListener { view ->
            if (news_detail_edittext.text.isNotEmpty()) {
                news_detail_edittext.error = null
                val body = NewsCommentRequest(newsId, news_detail_edittext.text.toString())
                viewModel.newsCommentPost(body).observe(this, Observer {
                    if (it) {
                        Toast.makeText(context, "Ваши коментарии отправлены!", Toast.LENGTH_SHORT)
                            .show()
                        news_detail_edittext.setText(" ")
                        news_detail_edittext.clearFocus()
                        MyUtils.hideKeyboard(activity!!, view)

                    } else {
                        Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                news_detail_edittext.error = "Коментарии не могут быть пустыми"
            }
        }
    }


    private fun initViews() {
        MainActivity.alert.show()
        viewModel.newsDetail(newsId).observe(this, Observer { item ->
            news_detail_sender.text = item.personName + " • " + MyUtils.toMyDateTime(item.postDate)
            news_detail_title.text = item.title
            news_detail_content.text = item.content

            val fileAdapter = NewsFilesAdapter(item.attachments, this)

            if (fileAdapter.itemCount == 0) {
                news_detail_fasten_files.visibility = View.GONE
                news_detail_files_rv.visibility = View.GONE
            } else {
                news_detail_files_rv.adapter = fileAdapter
            }
        })

        viewModel.newsComment(newsId).observe(this, Observer { item ->
            val commentAdapter = NewsCommentAdapter(item)
            if (commentAdapter.itemCount == 0) {
                news_detail_comment.visibility = View.GONE
                news_detail_comment_rv.visibility = View.GONE
            } else {
                news_detail_comment_rv.adapter = commentAdapter
            }
            MainActivity.alert.hide()
        })
    }

    private fun initArguments() {
        newsId = try {
            arguments!!.getInt("newsId")
        } catch (e: Exception) {
            0
        }
    }

    override fun onClickItem(position: Int, url: String) {
        filePath = url

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
        Toast.makeText(context,"Файл загружается...",Toast.LENGTH_LONG).show()
        val reguest = DownloadManager.Request(Uri.parse(downloadUrl))
        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        reguest.setTitle("TSJ.DOM")
        reguest.setDescription("Файл загружается...")

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
                    downloadFile(filePath)
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
