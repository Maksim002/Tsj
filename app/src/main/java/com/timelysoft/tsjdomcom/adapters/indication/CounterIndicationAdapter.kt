package com.timelysoft.tsjdomcom.adapters.indication

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class CounterIndicationAdapter (item: ArrayList<CounterIndicationModel> = arrayListOf()): GenericRecyclerAdapter<CounterIndicationModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_counter_indication)
    }

    override fun bind(item: CounterIndicationModel, holder: ViewHolder) {

    }

}