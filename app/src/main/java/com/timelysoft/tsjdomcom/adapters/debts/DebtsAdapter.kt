package com.timelysoft.tsjdomcom.adapters.debts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.counter.DebtModel
import com.timelysoft.tsjdomcom.service.model.counter.DebtsModel
import kotlinx.android.synthetic.main.item_debts.view.*

class DebtsAdapter (item: ArrayList<DebtModel> = arrayListOf()): GenericRecyclerAdapter<DebtModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_debts)
    }

    override fun bind(item: DebtModel, holder: ViewHolder) {
        holder.itemView.debts_address.text = item.address.toString()
        holder.itemView.debts_apartment.text = item.placementNumber
        holder.itemView.debts_debt.text = item.paymentSum.toString()
    }
}