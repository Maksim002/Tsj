package com.timelysoft.tsjdomcom.adapters.balance

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.BalanceStatusModel
import kotlinx.android.synthetic.main.item_balance.view.*

class BalanceAdapter(date: ArrayList<BalanceStatusModel> = ArrayList()) : GenericRecyclerAdapter<BalanceStatusModel>(
    date) {

    override fun bind(item: BalanceStatusModel, holder: ViewHolder) {
        holder.itemView.balance_recycler_title.text = item.serviceName
        holder.itemView.balance_recycler_cost.text = item.balance.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_balance)
    }

}