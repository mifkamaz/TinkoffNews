package ru.mifkamaz.tinkoffnews.data.news.source.remote

import kotlinx.coroutines.experimental.launch
import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSource
import ru.mifkamaz.tinkoffnews.utlis.AppExecutors
import ru.mifkamaz.tinkoffnews.utlis.awaitSafe
import timber.log.Timber

class NewsRemoteDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val newsService: NewsService
) : NewsDataSource {

    companion object {

        private var INSTANCE: NewsDataSource? = null;

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, newsService: NewsService): NewsDataSource {
            if (INSTANCE == null) {
                synchronized(NewsRemoteDataSource::javaClass) {
                    INSTANCE = NewsRemoteDataSource(appExecutors, newsService)
                }
            }
            return INSTANCE!!
        }

    }

    override fun loadAllNews(loadAllNewsCallback: NewsDataSource.LoadAllNewsCallback) {
        launch(appExecutors.networkIO()) {
            val result: Response<List<NewsTitleDto>>? = newsService.getNewsTitleDtos().awaitSafe()
            if (result != null && result.isOk() && result.payload != null) {
                launch(appExecutors.mainThread()) {
                    loadAllNewsCallback.onLoaded(NewsFromTitleDtoConverter.convertList(result.payload))
                }
            } else {
                launch(appExecutors.mainThread()) { loadAllNewsCallback.onError() }
            }
        }
    }

    override fun loadNews(id: String, loadNewsCallback: NewsDataSource.LoadNewsCallback) {
        Timber.v(id)
        launch(appExecutors.networkIO()) {
            val result: Response<NewsDto>? = newsService.getNewsDto(id).awaitSafe()
            if (result != null && result.isOk()) {
                launch(appExecutors.mainThread()) {
                    loadNewsCallback.onLoaded(NewsFromDtoConverter.convert(result.payload))
                }
            } else {
                launch(appExecutors.mainThread()) { loadNewsCallback.onError() }
            }
        }
    }

    override fun save(news: News) { }

    override fun refresh() { }
}