package com.timelysoft.tsjdomcom.ui.history

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.invoice.InvoiceAdapter
import com.timelysoft.tsjdomcom.adapters.payments.PaymentsAdapter
import com.timelysoft.tsjdomcom.adapters.invoice.InvoiceListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_history_detail.*
import java.lang.Exception

class HistoryDetailFragment : Fragment(),
    InvoiceListener {
    private val STORAGE_PERMISION_CODE: Int = 1000
    private lateinit var invoiceAdapter: InvoiceAdapter
    private lateinit var paymentsAdapter: PaymentsAdapter
    private lateinit var viewModel: HistoryViewModel
    private var to: String? = ""
    private var from: String? = ""
    private var servicesId: Int = 0
    private var operationsId: Int = 0
    private var placementId: Int = 0
    private var downloadUrl: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        initViews(root)
        return root
    }

    private fun initRV() {
        invoiceAdapter =
            InvoiceAdapter(this)
        history_detail_recycler_payments.adapter = invoiceAdapter
        paymentsAdapter = PaymentsAdapter()
        history_detail_recycler_invoice.adapter = paymentsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initRV()
    }

    private fun initArguments() {

        val id = try {
            arguments!!.getInt("res")
        } catch (e: Exception) {
            0
        }

        val serviceName = try {
            arguments!!.getString("serviceName")
        } catch (e: Exception) {
            ""
        }
        servicesId = try {
            arguments!!.getInt("servicesId")
        } catch (e: Exception) {
            0
        }

        val operationName = try {
            arguments!!.getString("operationName")
        } catch (e: Exception) {
            ""
        }
        operationsId = try {
            arguments!!.getInt("operationsId")
        } catch (e: Exception) {
            0
        }

        val address = try {
            arguments!!.getString("address")
        } catch (e: Exception) {
            ""
        }
        placementId = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        to = try {
            arguments!!.getString("to")
        } catch (e: Exception) {
            ""
        }
        from = try {
            arguments!!.getString("from")
        } catch (e: Exception) {
            ""
        }

        history_detail_list_currant.text = "Лицевой счет №$id"
        history_detail_text_address.text = address.toString()
        history_detail_text_operation_name.text = operationName.toString()
        history_detail_text_service_name.text = serviceName.toString()
        history_detail_text_to_from.text = "История оплат с $to - $from"
    }

    private fun initViews(root: View) {
    }

    override fun onStart() {
        super.onStart()
        MainActivity.alert.show()

        viewModel.invoices(placementId, servicesId, operationsId, MyUtils.toServerDate(from!!), MyUtils.toServerDate(to!!)).observe(this, Observer { result->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    if (data!!.paymentsHistory.size != 0) {
                        paymentsAdapter.update(data.paymentsHistory)
                        history_detail_recycler_invoice.visibility = View.VISIBLE
                        history_detail_payments.visibility = View.VISIBLE
                    } else {
                        invoiceAdapter.update(data.invoicesHistory)
                        history_detail_recycler_payments.visibility = View.VISIBLE
                        history_detail_accounts.visibility = View.VISIBLE
                    }
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClickDownload(id: Int?) {
        MainActivity.alert.show()
        viewModel.download(id!!).observe(this, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    this.downloadUrl = data!!
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(
                                context!!,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) == PackageManager.PERMISSION_DENIED
                        ) {
                            //permission denied
                            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                            //show popup to request runtime permission
                            requestPermissions(permissions, STORAGE_PERMISION_CODE)


                        } else {
                            //permission already granted
                            downloadFile(data)

                        }
                    } else {
                        //system OS is < Marshmallow
                        //pickImageFromGallery()
                        downloadFile(data)
                    }
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun downloadFile(downloadUrl: String) {
        val reguest = DownloadManager.Request(Uri.parse(downloadUrl))
        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        reguest.setTitle(MyUtils.fileName(downloadUrl))
        reguest.setDescription("Файл загружаеться.....")

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
