package ru.mifkamaz.tinkoffnews.data.news.source

import ru.mifkamaz.tinkoffnews.data.news.News

interface NewsDataSource {

    fun loadAllNews(loadAllNewsCallback: LoadAllNewsCallback)

    fun loadNews(id : String, loadNewsCallback: LoadNewsCallback)

    fun save(newsList: List<News>) { newsList.forEach { save(it) }}

    fun save(news: News)

    fun refresh()

    interface LoadAllNewsCallback {

        fun onLoaded(newsList: List<News>)

        fun onError()

    }

    interface LoadNewsCallback {

        fun onLoaded(news: News)

        fun onError()

    }

}