package com.example.tsj.adapters.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.news.NewsCommentsModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.item_news_comment.view.*

class NewsCommentAdapter(item: List<NewsCommentsModel>) :
    GenericRecyclerAdapter<NewsCommentsModel>(item) {
    override fun bind(item: NewsCommentsModel, holder: ViewHolder) {
        holder.itemView.item_comment_title.text = item.personName + " • " + MyUtils.toMyDateTime(item.postDate)
        holder.itemView.item_comment_content.text = item.message
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_news_comment)
    }
}