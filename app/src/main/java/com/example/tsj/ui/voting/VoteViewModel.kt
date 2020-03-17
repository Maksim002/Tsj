package com.example.tsj.ui.voting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.model.VoteModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AddressModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteViewModel : ViewModel() {
//dastan
    fun voteList(typeId: Int, id: Int): LiveData<List<VoteModel>> {
        val data = MutableLiveData<List<VoteModel>>()

        RetrofitService.apiService().votingList(typeId, id)
            .enqueue(object : Callback<List<VoteModel>> {
                override fun onFailure(call: Call<List<VoteModel>>, t: Throwable) {
                    println("")
                }

                override fun onResponse(
                    call: Call<List<VoteModel>>,
                    response: Response<List<VoteModel>>
                ) {
                    data.value = response.body()
                }

            })
        return data
    }

    fun voteAddress(): LiveData<List<AddressModel>> {
        val data = MutableLiveData<List<AddressModel>>()

        RetrofitService.apiService().votingAddress()
            .enqueue(object : Callback<List<AddressModel>> {
                override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                    println()
                }

                override fun onResponse(
                    call: Call<List<AddressModel>>,
                    response: Response<List<AddressModel>>
                ) {
                    data.value = response.body()
                }

            })
        return data
    }
}