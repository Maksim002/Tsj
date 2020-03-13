package com.example.tsj.adapters.references

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.ReferenceModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.item_personal.view.number
import kotlinx.android.synthetic.main.item_references.view.*

class AdapterReferences(items: ArrayList<ReferenceModel>) :
    GenericRecyclerAdapter<ReferenceModel>(items) {
    override fun bind(item: ReferenceModel, holder: ViewHolder) {
        holder.itemView.number.text = item.certificateNumber.toString()
        holder.itemView.name.text = item.fullName
        holder.itemView.date.text = MyUtils.toMyDate(item.forDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_references)
    }
}