package com.example.tsj.ui.personal

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.CurrentBalance
import com.example.tsj.service.model.InvoicesAccounts
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PersonalViewModel : ViewModel(){

        fun invoices(
            servicesId: Int,
            operationsId: Int,
            placementId: Int,
            tos: String?,
            froms: String?
        ): LiveData<CurrentBalance> {
            val data = MutableLiveData<CurrentBalance>()
            RetrofitService.apiServise().invoices(placementId,servicesId,operationsId,tos!!,froms!!).enqueue(object : Callback<CurrentBalance> {
                override fun onFailure(call: Call<CurrentBalance>, t: Throwable) {
                    println()
                }

                override fun onResponse(
                    call: Call<CurrentBalance>,
                    response: Response<CurrentBalance>) {

                    if (response.isSuccessful){
                        data.value = response.body()
                    }
                }
            })
            return data
        }
//    fun startDownloading(){
//        val reguest = DownloadManager.Request(Uri.parse())
//        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
//        reguest.setTitle("DownLoad")
//        reguest.setDescription("The file is downloading ...")
//
//        reguest.allowScanningByMediaScanner()
//        reguest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//        reguest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${System.currentTimeMillis()}")
//
//    }
}