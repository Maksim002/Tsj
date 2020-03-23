package com.example.tsj.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.MessageItemModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.item_message.view.*


class MessageAdapter(messageId : Int, listener: MessageClicklItemListener, items: List<MessageItemModel>) :
    GenericRecyclerAdapter<MessageItemModel>(items) {
    private var listener: MessageClicklItemListener = listener

    private var id : Int = messageId
    private val outBox = 0

    override fun bind(item: MessageItemModel, holder: ViewHolder) {
        holder.itemView.setOnClickListener { listener.onClickMessage(item) }
        holder.itemView.msg_title.text = item.title
        val date = MyUtils.toMyDate(item.sendDate)
        holder.itemView.msg_date.text = date

        if (id == outBox){
            holder.itemView.msg_sender.text = "Кому: " + item.personName

        }else{
            holder.itemView.msg_sender.text = "От кого: " + item.personName
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_message)
    }

}