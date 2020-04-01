package com.timelysoft.tsjdomcom.adapters.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.files.GeneralClickListener
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.news.NewsAttachments
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