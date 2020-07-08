package com.timelysoft.tsjdomcom.adapters.report

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class PaymentReportAdapter (item: ArrayList<PaymentReportModel> = arrayListOf()): GenericRecyclerAdapter<PaymentReportModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_payment_report)
    }

    override fun bind(item: PaymentReportModel, holder: ViewHolder) {
    }
}