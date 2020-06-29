package com.timelysoft.tsjdomcom.adapters.provider

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_provider.view.*
import java.util.*
import kotlin.collections.ArrayList

class ProviderAdapter (item: ArrayList<ProviderModel> = arrayListOf()): GenericRecyclerAdapter<ProviderModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_provider)
    }

    override fun bind(item: ProviderModel, holder: ViewHolder) {
        holder.itemView.provider_company.text = item.title

        holder.itemView.provider_to_change.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("true", true)
            it.findNavController().navigate(R.id.navigation_create_supplier, bundle)
        }
    }
}