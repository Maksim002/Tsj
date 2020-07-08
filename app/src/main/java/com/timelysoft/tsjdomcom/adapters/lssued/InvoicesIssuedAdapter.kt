package com.timelysoft.tsjdomcom.adapters.lssued

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class InvoicesIssuedAdapter (item: ArrayList<InvoicesLssuedModel> = arrayListOf()):  GenericRecyclerAdapter<InvoicesLssuedModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_invoice_issued)
    }

    override fun bind(item: InvoicesLssuedModel, holder: ViewHolder) {

    }
}