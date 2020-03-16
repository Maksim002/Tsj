package com.example.tsj.adapters.families

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.request.RelativeRequest
import kotlinx.android.synthetic.main.item_families.view.*

class FamilyAdapter(
    private val listener: FamilyListener,
    item: ArrayList<RelativeRequest> = ArrayList()
) :
    GenericRecyclerAdapter<RelativeRequest>(item) {

    override fun bind(item: RelativeRequest, holder: ViewHolder) {
        holder.itemView.text_relative.text = item.relative
        holder.itemView.text_name.text = item.fullName
        holder.itemView.text_date.text = item.date
        holder.itemView.image_delete.setOnClickListener {
            listener.onClickDelete(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_families)
    }
}