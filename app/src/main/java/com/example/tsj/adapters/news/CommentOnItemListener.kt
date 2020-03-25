package com.example.tsj.adapters.news

import com.example.tsj.service.model.news.NewsCommentsModel

interface CommentOnItemListener {

    fun newsCommentButton(model: NewsCommentsModel)

}