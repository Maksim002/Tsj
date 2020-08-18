package com.timelysoft.tsjdomcom.ui.counter

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.debts.DebtsAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.counter.DebtModel
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoices
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_debts.*
import kotlinx.android.synthetic.main.fragment_supplier_accounts.*

class DebtsFragment : Fragment() {
    private var viewModel = CounterViewModel()
    private var myAdapter = DebtsAdapter()
    private val STORAGE_PERMISION_CODE: Int = 1000
    private var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
        initRequest()
    }

    private fun initRequest() {
        MainActivity.alert.show()
        viewModel.debtsInformationTsj().observe(viewLifecycleOwner, Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    debts_tsj.setText(data!!.name)
                    debts_home.setText(data.address)
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
            MainActivity.alert.hide()
        })

        debts_choose.setOnClickListener {
            MainActivity.alert.show()
            viewModel.debtsSaveExcel().observe(viewLifecycleOwner, Observer { result->
                val msg = result.msg
                val data = result.data
                when(result.status){
                    Status.SUCCESS ->{
                        downloadTemplate(data!!)
                    }
                    Status.NETWORK, Status.ERROR ->{
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
                MainActivity.alert.hide()
            })
        }
    }

    private fun initRecyclerView() {
        MainActivity.alert.show()
        debts_recycler.adapter = myAdapter
        viewModel.counterListDebts().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    myAdapter.update(data!!.debts as ArrayList<DebtModel>)
                    myAdapter.notifyDataSetChanged()
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
            MainActivity.alert.hide()
        })
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