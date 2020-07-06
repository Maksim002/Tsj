package com.timelysoft.tsjdomcom.adapters.history

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class PaymentHistoryAdapter (item: ArrayList<PaymentHistoryModel> = arrayListOf()): GenericRecyclerAdapter<PaymentHistoryModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_payment_history)
    }

    override fun bind(item: PaymentHistoryModel, holder: ViewHolder) {

    }
}