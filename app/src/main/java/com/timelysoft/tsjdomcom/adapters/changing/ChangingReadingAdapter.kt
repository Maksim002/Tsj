package com.timelysoft.tsjdomcom.adapters.changing

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_changing_reading.view.*

class ChangingReadingAdapter (item: ArrayList<ChangingReadingModel> = arrayListOf()): GenericRecyclerAdapter<ChangingReadingModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_changing_reading)
    }

    override fun bind(item: ChangingReadingModel, holder: ViewHolder) {

    }

}