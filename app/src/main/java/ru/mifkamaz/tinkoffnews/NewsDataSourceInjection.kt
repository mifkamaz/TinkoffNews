package ru.mifkamaz.tinkoffnews

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSource
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSourceImpl
import ru.mifkamaz.tinkoffnews.data.news.source.in_memory.NewsInMemoryDataSource
import ru.mifkamaz.tinkoffnews.data.news.source.local.NewsDatabase
import ru.mifkamaz.tinkoffnews.data.news.source.local.NewsLocalDataSource
import ru.mifkamaz.tinkoffnews.data.news.source.remote.NewsRemoteDataSource
import ru.mifkamaz.tinkoffnews.data.news.source.remote.NewsService
import ru.mifkamaz.tinkoffnews.utlis.AppExecutors

object NewsDataSourceInjection {

    fun provide(context: Context): NewsDataSource = NewsDataSourceImpl.getInstance(
            NewsInMemoryDataSource.getInstance(AppExecutors()),
            NewsLocalDataSource.getInstance(AppExecutors(), newsDao(context)),
            NewsRemoteDataSource.getInstance(AppExecutors(), newsService())
    )

    private fun newsDao(context: Context) = NewsDatabase.getInstance(context).newsDao()

    private fun newsService(): NewsService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
                .callFactory(httpClient.build())
                .baseUrl("https://api.tinkoff.ru/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create<NewsService>(NewsService::class.java)
    }

}