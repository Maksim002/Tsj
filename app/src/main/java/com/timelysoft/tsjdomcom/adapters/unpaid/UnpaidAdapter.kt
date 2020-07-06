package com.timelysoft.tsjdomcom.adapters.unpaid

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class UnpaidAdapter (item: ArrayList<UnpaidModel> = arrayListOf()): GenericRecyclerAdapter<UnpaidModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_unpaid)
    }

    override fun bind(item: UnpaidModel, holder: ViewHolder) {

    }
}