package com.timelysoft.tsjdomcom.adapters.edit

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class EditEstimateAdapter (item: ArrayList<EditEstimateModel> = arrayListOf()): GenericRecyclerAdapter<EditEstimateModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_edit_estemate)
    }

    override fun bind(item: EditEstimateModel, holder: ViewHolder) {
    }
}