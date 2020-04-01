package com.timelysoft.tsjdomcom.adapters.news

import com.timelysoft.tsjdomcom.service.model.news.NewsCommentsModel

interface CommentOnItemListener {

    fun newsCommentButton(model: NewsCommentsModel)

}