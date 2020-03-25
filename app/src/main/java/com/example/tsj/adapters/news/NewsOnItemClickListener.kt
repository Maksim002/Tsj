package com.example.tsj.adapters.news

import com.example.tsj.service.model.news.NewsModel

interface NewsOnItemClickListener {

    fun newsItemOnClicked (newsModel : NewsModel)

}