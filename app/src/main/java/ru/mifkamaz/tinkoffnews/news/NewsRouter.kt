package ru.mifkamaz.tinkoffnews.news

import android.content.Context
import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.news_detail.NewsDetailActivity

class NewsRouter(context: Context) : NewsContract.Router(context) {

    override fun openNewsDetails(news: News) {
        start(NewsDetailActivity.newInstance(context, news.id!!))
    }

}