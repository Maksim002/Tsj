package com.example.tsj.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.news.NewsViewHolder
import com.example.tsj.model.NewsModel

class NewsAdapter() : RecyclerView.Adapter<NewsViewHolder>() {

    private var model: List<NewsModel> = ArrayList()

    fun submitList(list: List<NewsModel>) {
        model = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(model[position])
    }

    override fun getItemCount(): Int {
        return model.size
    }
}