package com.timelysoft.tsjdomcom.adapters.payments

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.PaymentHistory
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_payments.view.*

class PaymentsAdapter(item: List<PaymentHistory> = ArrayList() ): GenericRecyclerAdapter<PaymentHistory>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_payments)
    }

    override fun bind(item: PaymentHistory, holder: ViewHolder) {
        holder.itemView.payment.text = item.personName
        holder.itemView.scrip.text = item.amount.toString()
        holder.itemView.number.text = MyUtils.toMyDate(item.date.toString())
    }

}