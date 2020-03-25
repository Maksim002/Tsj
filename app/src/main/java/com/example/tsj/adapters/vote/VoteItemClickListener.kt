package com.example.tsj.adapters.vote

import com.example.tsj.service.model.VoteModel

interface VoteItemClickListener {

    fun onClick(model: VoteModel, isCanVote: Boolean)

}