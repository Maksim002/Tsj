package com.example.tsj.adapters.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.files.GeneralClickListener
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.news.NewsAttachments
import kotlinx.android.synthetic.main.item_news_file.view.*

class NewsFilesAdapter(item: List<NewsAttachments>, private val fileListener: GeneralClickListener) :
    GenericRecyclerAdapter<NewsAttachments>(item) {

    override fun bind(item: NewsAttachments, holder: ViewHolder) {

        holder.itemView.news_file_title.text = item.fileName

        holder.itemView.news_file_download.setOnClickListener {
            fileListener.onClickItem(holder.adapterPosition, item.filePath, item.fileName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_news_file)
    }
}