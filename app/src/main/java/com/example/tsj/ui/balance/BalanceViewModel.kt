package com.example.tsj.ui.balance

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AddressListModel
import com.example.tsj.service.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BalanceViewModel : ViewModel() {




    fun addresses(): LiveData<List<AddressListModel>> {
        val data = MutableLiveData<List<AddressListModel>>()

        RetrofitService.apiServise().addresses().enqueue(object : Callback<List<AddressListModel>> {
            override fun onResponse(call: Call<List<AddressListModel>>, response: Response<List<AddressListModel>>){
                if (response.isSuccessful){
                    data.value = response.body()

                }
            }

            override fun onFailure(call: Call<List<AddressListModel>>, t: Throwable) {
                println()
            }
        })

        return data
    }
}