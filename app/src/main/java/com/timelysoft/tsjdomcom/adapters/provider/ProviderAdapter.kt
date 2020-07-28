package com.timelysoft.tsjdomcom.adapters.provider

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.provider.ProviderModel
import kotlinx.android.synthetic.main.item_provider.view.*
import kotlin.collections.ArrayList

class ProviderAdapter (var listener: ProviderListener,var item: ArrayList<ProviderModel> = arrayListOf()): GenericRecyclerAdapter<ProviderModel>(item){

    fun listener(listener: ProviderListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_provider)
    }

    override fun bind(item: ProviderModel, holder: ViewHolder) {
        holder.itemView.provider_company.text = item.name
        holder.itemView.provider_type.text = "Тип организации: " + item.organizationType
        holder.itemView.provider_inn.text = "ИНН: " + item.tin
        holder.itemView.provider_okpo.text = "ОКПО: " + item.okpo
        holder.itemView.provider_telephone.text = "Телефон: " + item.phone
        holder.itemView.provider_account.text = "Расчётный счёт: " + item.checkingAccount

        holder.itemView.provider_to_change.setOnClickListener {
            listener.editClick(holder.adapterPosition, item)
        }
        holder.itemView.provider_delete.setOnClickListener {
            listener.clearProviderClick(item)
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, items.size)
        }
    }
}