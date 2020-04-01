package com.timelysoft.tsjdomcom.adapters.vote

import com.timelysoft.tsjdomcom.service.model.VoteModel

interface VoteItemClickListener {

    fun onClick(model: VoteModel, isCanVote: Boolean)

}