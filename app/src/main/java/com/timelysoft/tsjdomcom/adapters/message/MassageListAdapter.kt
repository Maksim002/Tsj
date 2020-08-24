package com.timelysoft.tsjdomcom.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class MassageListAdapter( item: ArrayList<MassageListModel> = arrayListOf()): GenericRecyclerAdapter<MassageListModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_massage_list_fragment)
    }

    override fun bind(item: MassageListModel, holder: ViewHolder) {

    }
}