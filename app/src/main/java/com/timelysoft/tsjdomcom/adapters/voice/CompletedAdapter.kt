package com.timelysoft.tsjdomcom.adapters.voice

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class CompletedAdapter (item: ArrayList<VoiceCompletionModel> = arrayListOf()): GenericRecyclerAdapter<VoiceCompletionModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_completed)
    }

    override fun bind(item: VoiceCompletionModel, holder: ViewHolder) {
    }
}