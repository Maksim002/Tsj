package com.timelysoft.tsjdomcom.ui.personal

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
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.pesonal.PersonalAdapterAccounts
import com.timelysoft.tsjdomcom.adapters.pesonal.PersonalAdapterPayments
import com.timelysoft.tsjdomcom.utils.MyUtils
import java.lang.Exception

class PersonalFragment : Fragment(), PersonalListener {
    private val STORAGE_PERMISSION_CODE: Int = 1000
    private lateinit var adapterPayments: PersonalAdapterPayments
    private lateinit var adapterAccount: PersonalAdapterAccounts
    private lateinit var recyclerViewPlatei: RecyclerView
    private lateinit var recyclerViewAccount: RecyclerView
    private lateinit var textCurrant: TextView
    private lateinit var textAddress: TextView
    private lateinit var textOperation: TextView
    private lateinit var layoutAccounts: LinearLayout
    private lateinit var layoutPayments: LinearLayout
    private lateinit var textService: TextView
    private lateinit var textToFrom: TextView
    private lateinit var textBalance: TextView
    private lateinit var viewModel: PersonalViewModel
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
        val root = inflater.inflate(R.layout.fragment_personal, container, false)
        viewModel = ViewModelProviders.of(this).get(PersonalViewModel::class.java)
        initViews(root)
        return root
    }

    private fun initRV() {
        adapterPayments = PersonalAdapterPayments(this)
        recyclerViewPlatei.adapter = adapterPayments
        adapterAccount = PersonalAdapterAccounts()
        recyclerViewAccount.adapter = adapterAccount
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

        textCurrant.text = "Лицевой счет №$id"
        textAddress.text = address.toString()
        textOperation.text = operationName.toString()
        textService.text = serviceName.toString()
        textToFrom.text = "История оплат с $to - $from"
    }

    private fun initViews(root: View) {
        textCurrant = root.findViewById(R.id.personal_list_currant)
        textAddress = root.findViewById(R.id.personal_text_address)
        textOperation = root.findViewById(R.id.personal_text_operation_name)
        textService = root.findViewById(R.id.personal_text_service_name)
        textToFrom = root.findViewById(R.id.personal_text_to_from)
        textBalance = root.findViewById(R.id.personal_text_balance)
        layoutAccounts = root.findViewById(R.id.personal_accounts)
        layoutPayments = root.findViewById(R.id.personal_payments)
        recyclerViewPlatei = root.findViewById(R.id.recyclerPersonal)
        recyclerViewAccount = root.findViewById(R.id.recyclerViewAccount)
    }

    override fun onStart() {
        super.onStart()
        MainActivity.alert.show()
        viewModel.invoices(
            servicesId,
            operationsId,
            placementId,
            MyUtils.toServerDate(from!!),
            MyUtils.toServerDate(to!!)
        )
            .observe(this, Observer {
                if (it.paymentsHistory?.size != 0) {
                    adapterAccount.listUpdate(it.paymentsHistory!!)
                    recyclerViewAccount.visibility = View.VISIBLE
                    layoutPayments.visibility = View.VISIBLE
                } else {
                    adapterPayments.listUpdate(it.invoicesHistory!!)
                    recyclerViewPlatei.visibility = View.VISIBLE
                    layoutAccounts.visibility = View.VISIBLE
                }
                MainActivity.alert.hide()

            })
    }

    override fun onClickDownload(id: Int?) {
        MainActivity.alert.show()
        viewModel.download(id).observe(this, Observer { url ->
            MainActivity.alert.hide()
            this.downloadUrl = url
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(
                        context!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, STORAGE_PERMISSION_CODE)


                } else {
                    //permission already granted
                    downloadFile(url)

                }
            } else {
                //system OS is < Marshmallow
                //pickImageFromGallery()
                downloadFile(url)
            }
        })
    }

    private fun downloadFile(downloadUrl: String) {
        val request = DownloadManager.Request(Uri.parse(downloadUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(MyUtils.fileName(downloadUrl))
        request.setDescription("Файл загружаеться.....")

        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "${System.currentTimeMillis()}"
        )

        val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
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
