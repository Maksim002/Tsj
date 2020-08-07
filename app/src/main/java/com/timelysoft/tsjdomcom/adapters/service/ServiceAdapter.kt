package com.timelysoft.tsjdomcom.adapters.service

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.service.AssociationServicesModel
import com.timelysoft.tsjdomcom.service.model.service.ServiceModel
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceAdapter (item: ArrayList<ServiceModel> = arrayListOf()): GenericRecyclerAdapter<ServiceModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_service)
    }

    override fun bind(item: ServiceModel, holder: ViewHolder) {
        holder.itemView.service_type.text = item.name
        holder.itemView.service_rate.text = item.tariff.toString()
        holder.itemView.service_type_service.text = item.servicePaidName

        if (item.isDependent == false){
            holder.itemView.service_dependence.text = "Нет"
        }else{
            holder.itemView.service_dependence.text = "Да"
        }

        holder.itemView.service_period.text = item.periodName

        if (item.isVolumeMultiplier == false){
            holder.itemView.service_plus_rate.text = "Нет"
        }else{
            holder.itemView.service_plus_rate.text = "Да"
        }

        if (item.isCounter == false){
            holder.itemView.service_counter.text = "Нет"
        }else{
            holder.itemView.service_counter.text = "Да"
        }
    }
}