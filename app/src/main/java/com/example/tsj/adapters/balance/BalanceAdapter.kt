package com.example.tsj.adapters.balance

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.BalanceStatusModel
import kotlinx.android.synthetic.main.item_balance.view.*

class BalanceAdapter(items: List<BalanceStatusModel> = ArrayList()) : GenericRecyclerAdapter<BalanceStatusModel>(items) {

    override fun bind(item: BalanceStatusModel, holder: ViewHolder) {
        holder.itemView.balance_recycler_title.text = item.serviceName
        holder.itemView.balance_recycler_cost.text = item.balance.toString()
    }

    fun setList(list: List<BalanceStatusModel>) {
        this.items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_balance)
    }

}