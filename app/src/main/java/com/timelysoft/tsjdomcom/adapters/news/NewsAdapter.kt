package com.timelysoft.tsjdomcom.adapters.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.news.NewsModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_file.view.*

class NewsAdapter(item: ArrayList<NewsModel>, listener: NewsOnItemClickListener) :
    GenericRecyclerAdapter<NewsModel>(item) {
    private val listener: NewsOnItemClickListener = listener
    override fun bind(item: NewsModel, holder: ViewHolder) {
        holder.itemView.titleF.text = item.title
        holder.itemView.addressF.text = MyUtils.toMyDateTime(item.postDate)
        holder.itemView.setOnClickListener { listener.newsItemOnClicked(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_file)
    }

}