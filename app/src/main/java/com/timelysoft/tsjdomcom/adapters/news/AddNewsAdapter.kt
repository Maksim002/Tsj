package com.timelysoft.tsjdomcom.adapters.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_add_news.view.*

class AddNewsAdapter (var listener: AddNewsListener, item: ArrayList<AddNewsModel> = arrayListOf()): GenericRecyclerAdapter<AddNewsModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_add_news)
    }

    override fun bind(item: AddNewsModel, holder: ViewHolder) {
        holder.itemView.add_news_file.setOnClickListener {
            listener.AddNewsOnClickListener(item)
            notifyDataSetChanged()
        }
        Glide.with(holder.itemView.add_news_file)
            .load(item.usl)
            .into(holder.itemView.add_news_file)
    }
}