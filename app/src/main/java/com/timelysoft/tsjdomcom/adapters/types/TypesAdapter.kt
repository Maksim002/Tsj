package com.timelysoft.tsjdomcom.adapters.types

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder


class TypesAdapter (item: ArrayList<TypesModel> = arrayListOf()): GenericRecyclerAdapter<TypesModel>(item){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_types)
    }

    override fun bind(item: TypesModel, holder: ViewHolder) {
    }
}