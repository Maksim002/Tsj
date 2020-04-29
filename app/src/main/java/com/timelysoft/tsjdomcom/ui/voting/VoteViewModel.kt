package com.timelysoft.tsjdomcom.ui.voting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
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
    private val repository = NetworkRepository()

    fun votes(typeId: Int, id: Int): LiveData<ResultStatus<List<VoteModel>>>{
        return repository.votes(typeId, id)
    }

    fun voteAddress(): LiveData<ResultStatus<List<AddressModel>>>{
        return repository.votingAddress()
    }

    fun voteTypes(): LiveData<ResultStatus<List<MessagesPersonsModel>>>{
        return repository.votingType()
    }

    fun voteVariantsN(id: Int): LiveData<ResultStatus<List<MessagesPersonsModel>>>{
        return repository.votingVariants(id)
    }

    fun votingPostN(body: VotingRequest): LiveData<ResultStatus<Nothing>>{
        return repository.votingPost(body)
    }

    fun voteDetailN(id: Int): LiveData<ResultStatus<VotingDetailModel>>{
        return repository.votingDetail(id)
    }
}