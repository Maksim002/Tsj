package com.timelysoft.tsjdomcom.adapters.notice

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class NoticeAdapter (item: ArrayList<NoticeModel> = arrayListOf()): GenericRecyclerAdapter<NoticeModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_notice)
    }

    override fun bind(item: NoticeModel, holder: ViewHolder) {

    }
}