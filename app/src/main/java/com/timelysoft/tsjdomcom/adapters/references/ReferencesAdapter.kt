package com.timelysoft.tsjdomcom.adapters.references

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.ReferenceLiteModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_references.view.*

class ReferencesAdapter(items: ArrayList<ReferenceLiteModel>, val listener: ReferencesListener) :
    GenericRecyclerAdapter<ReferenceLiteModel>(items) {
    override fun bind(item: ReferenceLiteModel, holder: ViewHolder) {
        holder.itemView.number.text = item.certificateNumber.toString()
        holder.itemView.name.text = item.fullName
        holder.itemView.date.text = MyUtils.toMyDate(item.forDate)
        if (item.certificateIssued){
            holder.itemView.reference_image.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tick, 0);
        }else{
            holder.itemView.reference_image.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_deny, 0);
        }
        holder.itemView.setOnClickListener {
            listener.onClickItem(item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_references)
    }
}