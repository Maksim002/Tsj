package com.timelysoft.tsjdomcom.adapters.news

import com.timelysoft.tsjdomcom.service.model.news.NewsModel

interface NewsOnItemClickListener {

    fun newsItemOnClicked (newsModel : NewsModel)

}