package ru.mifkamaz.tinkoffnews.data.news.source.remote

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("news")
    fun getNewsTitleDtos() : Deferred<Response<List<NewsTitleDto>>>

    @GET("news_content")
    fun getNewsDto(@Query("id") id : String) : Deferred<Response<NewsDto>>

}