package com.timelysoft.tsjdomcom.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_message_chairman.view.*

class MessageChairmanAdapter (item: ArrayList<MessageChairmanModel> = arrayListOf()): GenericRecyclerAdapter<MessageChairmanModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_message_chairman)
    }

    override fun bind(item: MessageChairmanModel, holder: ViewHolder) {
        holder.itemView.message_chairman_name.text = item.name

        holder.itemView.message_chairman_clear.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemChanged(holder.adapterPosition, items.size)
        }
    }
}