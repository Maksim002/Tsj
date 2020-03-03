package com.example.tsj.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.bid.BidClickItemListener
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.MessageModel
import kotlinx.android.synthetic.main.item_message.view.*


class MessageAdapter(listener : MessageCliclItemListener, items: ArrayList<MessageModel>) : GenericRecyclerAdapter<MessageModel>(items) {
    private var listener : MessageCliclItemListener = listener

    override fun bind(item: MessageModel, holder: ViewHolder) {
        holder.itemView.msg_sender.text = item.sender
        holder.itemView.msg_date.text = item.date
        holder.itemView.msg_title.text = item.title
        holder.itemView.msg_value.text = item.value

        holder.itemView.setOnClickListener { listener.onClickMessage(item)}
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_message)

    }
}