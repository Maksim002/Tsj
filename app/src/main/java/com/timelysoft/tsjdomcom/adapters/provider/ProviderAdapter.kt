package com.timelysoft.tsjdomcom.adapters.provider

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
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
        holder.itemView.provider_company.text = item.title

        holder.itemView.provider_to_change.setOnClickListener {
            listener.editClick(holder.adapterPosition)
        }
    }
}