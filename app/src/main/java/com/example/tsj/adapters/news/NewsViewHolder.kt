package com.example.tsj.adapters.news

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.service.model.NewsModel

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.titleF)
    var address: TextView = itemView.findViewById(R.id.addressF)

    fun bind(item: NewsModel) {
        title.text = item.title
        address.text = formateDate(item.postDate.toString())
    }

    fun formateDate(date: String): String {
        val yymmdd = date.substring(0, 10)
        yymmdd.replace('-', '.',  true)

        val hhmmss = date.substring(11, 19)
        hhmmss.replace(':', '.', true)

        val result = yymmdd + " " + hhmmss

        return result
    }
}
