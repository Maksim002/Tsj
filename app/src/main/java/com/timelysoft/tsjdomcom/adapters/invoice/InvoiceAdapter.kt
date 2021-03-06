package com.timelysoft.tsjdomcom.adapters.invoice

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.InvoicesAccounts
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_invoice.view.number
import kotlinx.android.synthetic.main.item_invoice.view.scrip
import kotlinx.android.synthetic.main.item_invoice.view.*

class InvoiceAdapter(private val listener: InvoiceListener, item: ArrayList<InvoicesAccounts> = ArrayList()): GenericRecyclerAdapter<InvoicesAccounts>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_invoice)
    }

    override fun bind(item: InvoicesAccounts, holder: ViewHolder) {
        holder.itemView.scrip.text = item.amount.toString()
        holder.itemView.number.text = MyUtils.toMyDate(item.date.toString())

        holder.itemView.icon.setOnClickListener {
            listener.onClickDownload(item.id)
        }
    }
}