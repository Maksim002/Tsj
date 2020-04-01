package com.timelysoft.tsjdomcom.adapters.news

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.news.NewsCommentsModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_news_comment.view.*

class NewsCommentAdapter(item: List<NewsCommentsModel>, listener: CommentOnItemListener) :
    GenericRecyclerAdapter<NewsCommentsModel>(item) {

    private val listener: CommentOnItemListener = listener

    override fun bind(item: NewsCommentsModel, holder: ViewHolder) {
        holder.itemView.item_comment_title.text =
            item.personName + " • " + MyUtils.toMyDateTime(item.postDate)
        holder.itemView.item_comment_content.text = item.message

        if (item.isDeletable) {
            holder.itemView.item_comment_delete.visibility = View.VISIBLE
            holder.itemView.item_comment_delete.setOnClickListener { listener.newsCommentButton(item) }
        } else {
            holder.itemView.item_comment_delete.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_news_comment)
    }
}