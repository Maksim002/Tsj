package com.timelysoft.tsjdomcom.adapters.voice

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class VoiceAdapter (var listener: VoiceListener,item: ArrayList<VoiceModel> = arrayListOf()): GenericRecyclerAdapter<VoiceModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_coice)
    }

    override fun bind(item: VoiceModel, holder: ViewHolder) {
        holder.itemView.setOnClickListener {
            listener.onClickVoiceListener(item)
        }
    }
}