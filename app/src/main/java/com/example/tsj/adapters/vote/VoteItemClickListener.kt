package com.example.tsj.adapters.vote

import com.example.tsj.model.VoteModel
import java.text.FieldPosition

 interface VoteItemClickListener {

    fun onVoteItemClicked(model: VoteModel)
}