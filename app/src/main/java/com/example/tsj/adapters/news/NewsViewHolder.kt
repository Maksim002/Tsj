package com.example.tsj.adapters.news

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.model.NewsModel

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.titleF)
    var address: TextView = itemView.findViewById(R.id.addressF)

    fun bind(get: NewsModel) {
        title.setText(get.title)
        address.setText(get.address)
    }

}
