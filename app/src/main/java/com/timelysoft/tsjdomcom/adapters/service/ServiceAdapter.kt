package com.timelysoft.tsjdomcom.adapters.service

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.service.AssociationServiceModel

class ServiceAdapter (item: ArrayList<AssociationServiceModel> = arrayListOf()): GenericRecyclerAdapter<AssociationServiceModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_service)
    }

    override fun bind(item: AssociationServiceModel, holder: ViewHolder) {
    }
}