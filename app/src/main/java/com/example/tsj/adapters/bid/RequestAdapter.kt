package com.example.tsj.adapters.bid

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.RequestsModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.item_bid.view.*

class RequestAdapter(private var listener: RequestClickItemListener, items: ArrayList<RequestsModel> = ArrayList()) :
    GenericRecyclerAdapter<RequestsModel>(items) {

    override fun bind(item: RequestsModel, holder: ViewHolder) {
        holder.itemView.bid_date.text = MyUtils.toMyDate(item.dateArrival)
        holder.itemView.bid_type.text = item.description
        holder.itemView.bid_title.text = item.requestTypeName
        holder.itemView.bid_status.text = item.statusName
//        if (item.status) {
//            holder.itemView.bid_status.text = "Создана"
//            holder.itemView.bid_status.setTextColor(Color.parseColor("#056525"))
//        } else {
//            holder.itemView.bid_status.text = "Отменен"
//            holder.itemView.bid_status.setTextColor(Color.parseColor("#FF4C13"))
//        }

        holder.itemView.setOnClickListener { listener.onClickRequest(item) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_bid)
    }
}