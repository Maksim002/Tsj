package com.timelysoft.tsjdomcom.adapters.families

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.RelativeModel
import kotlinx.android.synthetic.main.item_families.view.*

class FamilyAdapter(
    private val listener: FamilyListener,
    item: ArrayList<RelativeModel> = ArrayList()
) :
    GenericRecyclerAdapter<RelativeModel>(item) {

    override fun bind(item: RelativeModel, holder: ViewHolder) {
        holder.itemView.text_relative.text = item.relative
        holder.itemView.text_name.text = item.fullName
        holder.itemView.text_date.text = item.date
        holder.itemView.image_delete.setOnClickListener {
            listener.onClickDelete(holder.adapterPosition)
        }
        holder.itemView.setOnClickListener {
            listener.onClickItem(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_families)
    }
}