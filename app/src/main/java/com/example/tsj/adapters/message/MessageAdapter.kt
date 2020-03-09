package com.example.tsj.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.MessageModel
import kotlinx.android.synthetic.main.item_message.view.*


class MessageAdapter(listener: MessageClicklItemListener, items: List<MessageModel>) :
    GenericRecyclerAdapter<MessageModel>(items) {
    private var listener: MessageClicklItemListener = listener

    override fun bind(item: MessageModel, holder: ViewHolder) {
        holder.itemView.setOnClickListener { listener.onClickMessage(item) }

        holder.itemView.msg_sender.text = item.personName
        holder.itemView.msg_title.text = item.title
        holder.itemView.msg_date.text = item.sendDate
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_message)
    }

}