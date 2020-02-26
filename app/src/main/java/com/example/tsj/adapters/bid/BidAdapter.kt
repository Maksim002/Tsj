package com.example.tsj.adapters.bid

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.BidModel
import com.example.tsj.ui.bid.fragments.BidClickItemListener
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import kotlinx.android.synthetic.main.item_bid.view.*

class BidAdapter(listener: BidClickItemListener, items: ArrayList<BidModel>) : GenericRecyclerAdapter<BidModel>(items) {
    private var listener : BidClickItemListener = listener

    override fun bind(item: BidModel, holder: ViewHolder) {
        holder.itemView.bid_date.text = item.date
        holder.itemView.bid_type.text = item.type
        holder.itemView.bid_title.text = item.title

        if (item.status){
            holder.itemView.bid_status.text  = "Создана"
            holder.itemView.bid_status.setTextColor(Color.parseColor("#056525"))
        }else{
            holder.itemView.bid_status.text  = "Отменен"
            holder.itemView.bid_status.setTextColor(Color.parseColor("#FF4C13"))
        }

        holder.itemView.setOnClickListener { listener.onClickBid(item) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_bid)
    }
}