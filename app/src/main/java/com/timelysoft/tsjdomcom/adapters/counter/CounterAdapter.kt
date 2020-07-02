package com.timelysoft.tsjdomcom.adapters.counter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class CounterAdapter (item: ArrayList<CounterModel> = arrayListOf()): GenericRecyclerAdapter<CounterModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_counter)
    }

    override fun bind(item: CounterModel, holder: ViewHolder) {

    }

}