package com.example.tsj.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AddressModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BalanceViewModel : ViewModel() {




    fun addresses(): LiveData<List<AddressModel>> {
        val data = MutableLiveData<List<AddressModel>>()

        RetrofitService.apiServise().addresses().enqueue(object : Callback<List<AddressModel>> {
            override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>){
                if (response.isSuccessful){
                    data.value = response.body()

                }
            }

            override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                println()
            }
        })

        return data
    }
}