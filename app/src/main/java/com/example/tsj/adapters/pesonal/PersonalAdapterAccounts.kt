package com.example.tsj.adapters.pesonal

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.PaymentHistory
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.item_account.view.*

class PersonalAdapterAccounts(item: List<PaymentHistory> = ArrayList() ): GenericRecyclerAdapter<PaymentHistory>(item){

    fun listUpdate(items: List<PaymentHistory>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_account)
    }

    override fun bind(item: PaymentHistory, holder: ViewHolder) {
        holder.itemView.payment.text = item.personName
        holder.itemView.scrip.text = item.amount.toString()
        holder.itemView.number.text = MyUtils.toMyDate(item.date.toString())
    }

}