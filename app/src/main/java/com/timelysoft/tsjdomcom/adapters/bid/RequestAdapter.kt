package com.timelysoft.tsjdomcom.adapters.bid

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.RequestsModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_bid.view.*

class RequestAdapter(private var listener: RequestClickItemListener, items: ArrayList<RequestsModel> = ArrayList()) :
    GenericRecyclerAdapter<RequestsModel>(items) {

    override fun bind(item: RequestsModel, holder: ViewHolder) {
        holder.itemView.bid_date.text = MyUtils.toMyDate(item.dateArrival)
        holder.itemView.bid_type.text = item.description
        holder.itemView.bid_title.text = item.requestTypeName
        holder.itemView.bid_status.text = item.statusName

        holder.itemView.setOnClickListener { listener.onClickRequest(item) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_bid)
    }
}