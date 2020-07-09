package com.timelysoft.tsjdomcom.adapters.nested

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.edit.EstimateComponentModel
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class EditEstimateNestedAdapter (item: ArrayList<EstimateComponentModel> = arrayListOf()): GenericRecyclerAdapter<EstimateComponentModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_edit_estemate_nestes)
    }

    override fun bind(item: EstimateComponentModel, holder: ViewHolder) {

    }
}