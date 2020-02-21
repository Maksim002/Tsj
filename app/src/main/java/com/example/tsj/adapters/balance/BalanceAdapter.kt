package com.example.tsj.adapters.balance

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.BalanceModel
import kotlinx.android.synthetic.main.item_balance.view.*

class BalanceAdapter(items: ArrayList<BalanceModel>) : GenericRecyclerAdapter<BalanceModel>(items) {

    override fun bind(item: BalanceModel, holder: ViewHolder) {
        holder.itemView.balance_recycler_title.text = item.title
        holder.itemView.balance_recycler_cost.text = item.cost.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_balance)
    }

}