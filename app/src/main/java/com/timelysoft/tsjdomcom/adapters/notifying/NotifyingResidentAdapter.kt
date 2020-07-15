package com.timelysoft.tsjdomcom.adapters.notifying

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_notifying_resident.view.*

class NotifyingResidentAdapter (var listener: NotifyingResidentClickListener, item: ArrayList<NotifyingResidentModel> = arrayListOf()): GenericRecyclerAdapter<NotifyingResidentModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_notifying_resident)
    }

    override fun bind(item: NotifyingResidentModel, holder: ViewHolder) {
        holder.itemView.notifying_resident_title.text = item.address

        holder.itemView.setOnClickListener {
            listener.notifyingResidentClickListener(item)
        }
    }
}