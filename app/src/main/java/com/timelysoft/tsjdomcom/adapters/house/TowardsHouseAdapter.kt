package com.timelysoft.tsjdomcom.adapters.house

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class TowardsHouseAdapter (item: ArrayList<TowardsHouseModel> = arrayListOf()): GenericRecyclerAdapter<TowardsHouseModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_towards_house)
    }

    override fun bind(item: TowardsHouseModel, holder: ViewHolder) {

    }

}