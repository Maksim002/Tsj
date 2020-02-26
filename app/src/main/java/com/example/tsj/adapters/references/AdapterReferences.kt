package com.example.tsj.adapters.references

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.ReferencesModel
import kotlinx.android.synthetic.main.item_personal.view.number
import kotlinx.android.synthetic.main.item_references.view.*

class AdapterReferences(items: ArrayList<ReferencesModel>): GenericRecyclerAdapter<ReferencesModel>(items) {
    override fun bind(item: ReferencesModel, holder: ViewHolder) {
        holder.itemView.number.text = item.number
        holder.itemView.name.text = item.name
        holder.itemView.date.text = item.date

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_references)
    }
}