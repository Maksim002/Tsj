package com.example.tsj.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.BalanceStatusModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BalanceDetailViewModel : ViewModel() {

    fun services(placementId: Int): LiveData<List<BalanceStatusModel>> {
        val data = MutableLiveData<List<BalanceStatusModel>>()


        RetrofitService.apiServise().services(placementId)
            .enqueue(object : Callback<List<BalanceStatusModel>> {
                override fun onResponse(
                    call: Call<List<BalanceStatusModel>>,
                    response: Response<List<BalanceStatusModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<List<BalanceStatusModel>>, t: Throwable) {
                }
            })


        return data
    }
}