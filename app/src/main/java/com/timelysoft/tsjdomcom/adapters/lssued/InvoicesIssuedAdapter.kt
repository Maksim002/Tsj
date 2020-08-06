package com.timelysoft.tsjdomcom.adapters.lssued

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.payment.InvoicesIssuedModel

class InvoicesIssuedAdapter (item: ArrayList<InvoicesIssuedModel> = arrayListOf()):  GenericRecyclerAdapter<InvoicesIssuedModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_invoice_issued)
    }

    override fun bind(item: InvoicesIssuedModel, holder: ViewHolder) {

    }
}