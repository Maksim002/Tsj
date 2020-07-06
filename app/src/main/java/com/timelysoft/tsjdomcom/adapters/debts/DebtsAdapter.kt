package com.timelysoft.tsjdomcom.adapters.debts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class DebtsAdapter (item: ArrayList<DebtsModel> = arrayListOf()): GenericRecyclerAdapter<DebtsModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_debts)
    }

    override fun bind(item: DebtsModel, holder: ViewHolder) {

    }
}