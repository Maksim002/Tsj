package com.example.tsj.ui.personal

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
}