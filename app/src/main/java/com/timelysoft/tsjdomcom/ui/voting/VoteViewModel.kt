package com.timelysoft.tsjdomcom.ui.voting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.model.VoteModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.AddressModel
import com.timelysoft.tsjdomcom.service.model.MessagesPersonsModel
import com.timelysoft.tsjdomcom.service.model.vote.VotingDetailModel
import com.timelysoft.tsjdomcom.service.request.VotingRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteViewModel : ViewModel() {
    fun votes(typeId: Int, id: Int): LiveData<List<VoteModel>> {
        val data = MutableLiveData<List<VoteModel>>()
        RetrofitService.apiService().votes(typeId, id)
            .enqueue(object : Callback<List<VoteModel>> {
                override fun onFailure(call: Call<List<VoteModel>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<VoteModel>>,
                    response: Response<List<VoteModel>>
                ) {
                    if (response.isSuccessful)
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
                }

                override fun onResponse(
                    call: Call<List<AddressModel>>,
                    response: Response<List<AddressModel>>
                ) {
                    if (response.isSuccessful)
                        data.value = response.body()
                }

            })
        return data
    }

    fun voteTypes(): LiveData<List<MessagesPersonsModel>> {
        val data = MutableLiveData<List<MessagesPersonsModel>>()
        RetrofitService.apiService().votingType()
            .enqueue(object : Callback<List<MessagesPersonsModel>> {
                override fun onFailure(call: Call<List<MessagesPersonsModel>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<MessagesPersonsModel>>,
                    response: Response<List<MessagesPersonsModel>>
                ) {
                    if (response.isSuccessful)
                        data.value = response.body()
                }
            })

        return data
    }

    fun voteVariants(id: Int): LiveData<List<MessagesPersonsModel>> {
        val data = MutableLiveData<List<MessagesPersonsModel>>()
        RetrofitService.apiService().votingVariants(id)
            .enqueue(object : Callback<List<MessagesPersonsModel>> {
                override fun onFailure(call: Call<List<MessagesPersonsModel>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<MessagesPersonsModel>>,
                    response: Response<List<MessagesPersonsModel>>
                ) {
                    if (response.isSuccessful)
                        data.value = response.body()
                }
            })
        return data
    }

    fun votingPost(body: VotingRequest): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().votingPost(body).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.isSuccessful
            }

        })
        return data
    }

    fun voteDetail(id: Int): LiveData<VotingDetailModel> {
        val data = MutableLiveData<VotingDetailModel>()
        RetrofitService.apiService().votingDetail(id).enqueue(object : Callback<VotingDetailModel> {
            override fun onFailure(call: Call<VotingDetailModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<VotingDetailModel>,
                response: Response<VotingDetailModel>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }

            }

        })
        return data
    }
}