package com.timelysoft.tsjdomcom.ui.news


import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.files.GeneralClickListener
import com.timelysoft.tsjdomcom.adapters.news.CommentOnItemListener
import com.timelysoft.tsjdomcom.adapters.news.NewsCommentAdapter
import com.timelysoft.tsjdomcom.adapters.news.NewsFilesAdapter
import com.timelysoft.tsjdomcom.adapters.news.NewsVPAdapter
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.news.NewsAttachments
import com.timelysoft.tsjdomcom.service.model.news.NewsCommentsModel
import com.timelysoft.tsjdomcom.service.request.NewsCommentRequest
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailFragment : Fragment(), GeneralClickListener, CommentOnItemListener {

    private var filePath = " "
    private var fileName = " "
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
        if (AppPreferences.isLogined) {
            cardview.visibility = View.VISIBLE
            news_detail_send_btn.setOnClickListener { view ->
                MyUtils.hideKeyboard(activity!!, view!!)
                if (validate()) {
                    val body = NewsCommentRequest(newsId, news_detail_edittext.text.toString())
                    MainActivity.alert.show()

                    viewModel.newsCommentPost(body).observe(viewLifecycleOwner, Observer { result ->
                        val msg = result.msg
                        val data = result.data
                        MainActivity.alert.hide()
                        when(result.status){
                            Status.SUCCESS ->{
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    news_detail_edittext.setText("")
                                    news_detail_edittext.clearFocus()
                                    MyUtils.hideKeyboard(activity!!, view)
                                    initComments()
                            }
                            Status.ERROR, Status.NETWORK ->{
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        } else {
            cardview.visibility = View.GONE
        }
    }

    private fun initViews() {
        MainActivity.alert.show()

        viewModel.newsDetail(newsId).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    news_detail_sender.text = data!!.personName + " • " + MyUtils.toMyDateTime(data.postDate)
                    news_detail_title.text = data.title

                    val imageUrls = ArrayList<String>()
                    val filesList = ArrayList<NewsAttachments>()

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        news_detail_content.setText(Html.fromHtml(data.content,Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        news_detail_content.setText(Html.fromHtml(data.content));
                    }

                    for (i in data.attachments) {
                        if (MyUtils.isImage(i.fileName)) {
                            imageUrls.add(i.filePath)
                        } else {
                            filesList.add(i)
                        }
                    }

                    if (imageUrls.isNotEmpty()) {
                        news_detail_viewpager.adapter = NewsVPAdapter(requireContext(), imageUrls)
                    } else {
                        news_detail_viewpager.visibility = View.GONE
                    }

                    val fileAdapter = NewsFilesAdapter(filesList, this)

                    if (fileAdapter.itemCount == 0) {
                        news_detail_fasten_files.visibility = View.GONE
                        news_detail_files_rv.visibility = View.GONE
                    } else {
                        news_detail_files_rv.adapter = fileAdapter
                    }
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
        initComments()
    }

    private fun initComments() {
        MainActivity.alert.show()

        viewModel.newsComment(newsId).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    val commentAdapter = NewsCommentAdapter(data!! as ArrayList<NewsCommentsModel>, this)
                    if (commentAdapter.itemCount == 0) {
                        news_detail_comment.visibility = View.GONE
                        news_detail_comment_rv.adapter = commentAdapter
                    } else {
                        news_detail_comment.visibility = View.VISIBLE
                        news_detail_comment_rv.adapter = commentAdapter
                    }
                }
                Status.ERROR, Status.NETWORK ->{
                   Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initArguments() {
        newsId = try {
            arguments!!.getInt("newsId")
        } catch (e: Exception) {
            0
        }
    }

    override fun onClickItem(position: Int, url: String, fileName: String) {
        filePath = url
        this.fileName = fileName
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
        Toast.makeText(context, "Файл загружается...", Toast.LENGTH_LONG).show()
        val reguest = DownloadManager.Request(Uri.parse(downloadUrl))
        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        reguest.setTitle(fileName)
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
                    downloadFile(filePath)
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun newsCommentButton(model: NewsCommentsModel) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Вы действительно хотите удалить?")

        builder.setPositiveButton(getString(R.string.yesDelete)) { d: DialogInterface, i: Int ->
            MainActivity.alert.show()

            viewModel.newsCommentDelete(model.id).observe(this, Observer { result ->
                val msg = result.msg
                MainActivity.alert.hide()
                when(result.status){
                    Status.SUCCESS ->{
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR, Status.NETWORK ->{
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            initComments()
        }
        builder.setNegativeButton(getString(R.string.no)) { d: DialogInterface, i: Int ->
        }
        builder.show()
    }

    private fun validate(): Boolean {
        var valid = true
        if (news_detail_edittext.text.toString().isEmpty()) {
            text_bid_add_porch.error = "Поле не должно быть пустым"
            valid = false
        } else {
            text_bid_add_porch.isErrorEnabled = false
        }
        return valid
    }

}
