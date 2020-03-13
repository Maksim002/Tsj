package com.example.tsj.ui.reference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.ReferenceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReferenceViewModel : ViewModel() {
    fun getReferences(id:Int): LiveData<List<ReferenceModel>> {
        val data = MutableLiveData<List<ReferenceModel>>()
        RetrofitService.apiService().references(id).enqueue(object : Callback<List<ReferenceModel>> {
            override fun onFailure(call: Call<List<ReferenceModel>>, t: Throwable) {
            println()
            }

            override fun onResponse(
                call: Call<List<ReferenceModel>>,
                response: Response<List<ReferenceModel>>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }

            }

        })


        return data
    }


    fun addresses(): LiveData<List<AddressModel>> {
        val data = MutableLiveData<List<AddressModel>>()

        RetrofitService.apiService().addresses().enqueue(object : Callback<List<AddressModel>> {
            override fun onResponse(
                call: Call<List<AddressModel>>,
                response: Response<List<AddressModel>>
            ) {
                if (response.isSuccessful) {
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