package com.timelysoft.tsjdomcom.adapters.show

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_show_payment.view.*

class ShowPaymentAdapter (item: ArrayList<ShowPaymentModel> = arrayListOf()): GenericRecyclerAdapter<ShowPaymentModel>(item){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_show_payment)
    }

    override fun bind(item: ShowPaymentModel, holder: ViewHolder) {
        holder.itemView.show_payment_unpaid.setOnClickListener {
            it.findNavController().navigate(R.id.navigation_unpaid)
        }
    }
}