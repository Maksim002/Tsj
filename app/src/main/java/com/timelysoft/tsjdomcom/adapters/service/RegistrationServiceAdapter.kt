package com.timelysoft.tsjdomcom.adapters.service

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class RegistrationServiceAdapter (item: ArrayList<RegistrationServiceModel> = arrayListOf()): GenericRecyclerAdapter<RegistrationServiceModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_registration_service)
    }

    override fun bind(item: RegistrationServiceModel, holder: ViewHolder) {

    }

}