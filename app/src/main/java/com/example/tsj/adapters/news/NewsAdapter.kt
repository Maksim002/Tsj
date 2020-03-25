package com.example.tsj.adapters.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.news.NewsModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.item_file.view.*

class NewsAdapter(item: List<NewsModel>, listener: NewsOnItemClickListener) :
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