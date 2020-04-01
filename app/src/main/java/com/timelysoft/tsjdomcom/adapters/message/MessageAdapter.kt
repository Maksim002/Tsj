package com.timelysoft.tsjdomcom.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.MessageItemModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_message.view.*


class MessageAdapter(
    private var listener: MessageClicklItemListener,
    items: List<MessageItemModel>
) :
    GenericRecyclerAdapter<MessageItemModel>(items) {

    override fun bind(item: MessageItemModel, holder: ViewHolder) {
        holder.itemView.setOnClickListener { listener.onClickMessage(item) }
        holder.itemView.msg_title.text = item.title
        val date = MyUtils.toMyDate(item.sendDate)
        holder.itemView.msg_date.text = date
        holder.itemView.msg_sender.text = item.personName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_message)
    }

}