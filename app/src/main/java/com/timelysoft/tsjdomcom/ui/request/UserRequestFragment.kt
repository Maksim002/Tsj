package com.timelysoft.tsjdomcom.ui.request

import android.Manifest
import android.app.DatePickerDialog
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.request.UserRequestAdapter
import com.timelysoft.tsjdomcom.adapters.request.UserRequestListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.request.UserRequestTypeModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_user_request.*
import java.util.*

class UserRequestFragment : Fragment(), UserRequestListener {
    private var viewModel = RequestViewModel()
    private val STORAGE_PERMISION_CODE: Int = 1000

    private var mLastClickTime: Long = 0
    private var typeId: Int? = null
    private lateinit var dataFrom: String
    private lateinit var dataTo: String

    private var url: String = ""

    private var myAdapter = UserRequestAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAutoRequestFrom()
        getAutoRequestTo()
        getRequestUser()
        initRecycler()
    }

    override fun userRequestClick(item: Int) {
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

    override fun userRequestClickOwner(id: Int, position: Int) {
        val userBottomSheet = UserBottomSheet(id)
        userBottomSheet.show(fragmentManager!!, "UserBottomSheet")
    }

    private fun downloadFile(downloadUrl: String) {
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

    private fun initRecycler() {
        history_show.setOnClickListener {
            viewModel.listUser(dataFrom, dataTo, typeId)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                    val msg = result.msg
                    val data = result.data
                    when (result.status) {
                        Status.SUCCESS -> {
                            myAdapter.update(data!!)
                            myAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            user_request_recycler.adapter = myAdapter

            viewModel.userRequestSave(dataFrom, dataTo, typeId).observe(viewLifecycleOwner, androidx.lifecycle.Observer { result->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        url = data.toString()
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun getAutoRequestFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        user_request_date_from_out.keyListener = null;
        user_request_date_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                user_request_date_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        user_request_date_from_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dataFrom = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    }, year, month, day
                )
                picker.show()
                user_request_date_from_out.clearFocus()
            }
        }
    }

    private fun getAutoRequestTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        user_request_date_before_out.keyListener = null;
        user_request_date_before_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                user_request_date_before.defaultHintTextColor = col

                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        user_request_date_before_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dataTo = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    }, year, month, day
                )
                picker.show()
                user_request_date_before_out.clearFocus()
            }
        }
    }

    private fun getRequestUser() {
        var list: ArrayList<UserRequestTypeModel> = arrayListOf()
        viewModel.listUserType().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    val adapterRequestUser = data?.let {
                        ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                    }
                    user_request_type_out.setAdapter(adapterRequestUser)

                    list = data as ArrayList<UserRequestTypeModel>
                }
                Status.NETWORK, Status.ERROR -> {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
        user_request_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        user_request_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                user_request_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                user_request_type_out.clearFocus()
                typeId = list[position].id
            }
        user_request_type_out.setOnClickListener {
            user_request_type_out.showDropDown()
        }
        user_request_type_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        user_request_type_out.showDropDown()
                    }
                    if (!hasFocus && user_request_type_out.text!!.isNotEmpty()) {
                        user_request_type.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        user_request_type.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        user_request_type_out.clearFocus()
    }
}