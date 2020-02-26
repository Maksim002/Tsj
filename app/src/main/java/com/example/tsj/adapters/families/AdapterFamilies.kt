package com.example.tsj.adapters.families

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.FamiliesModel
import kotlinx.android.synthetic.main.item_families.view.*

class AdapterFamilies(item: ArrayList<FamiliesModel>): GenericRecyclerAdapter<FamiliesModel>(item){

    override fun bind(item: FamiliesModel, holder: ViewHolder) {
        holder.itemView.text_title.text = item.title
        holder.itemView.text_name.text = item.name
        holder.itemView.text_password.text = item.password
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_families)
    }
}