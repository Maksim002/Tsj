package com.example.tsj.adapters.message

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import kotlinx.android.synthetic.main.item_manadger.view.*

class ManagerAdapter(
    private val listener: GeneralClickListener,
    items: ArrayList<String> = ArrayList()
) : GenericRecyclerAdapter<String>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_manadger)
    }

    override fun bind(item: String, holder: ViewHolder) {
        holder.itemView.textUrl.text = item

        holder.itemView.imageId.setOnClickListener {
            listener.clickManager(holder.adapterPosition)
        }
    }

}