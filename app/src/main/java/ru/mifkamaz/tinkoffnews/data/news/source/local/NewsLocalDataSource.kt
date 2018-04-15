package ru.mifkamaz.tinkoffnews.data.news.source.local

import kotlinx.coroutines.experimental.launch
import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSource
import ru.mifkamaz.tinkoffnews.data.news.source.NewsUpdateRule
import ru.mifkamaz.tinkoffnews.utlis.AppExecutors

class NewsLocalDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val newsDao: NewsDao
) : NewsDataSource {

    companion object {

        private var INSTANCE: NewsDataSource? = null;

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, newsDao: NewsDao): NewsDataSource {
            if (INSTANCE == null) {
                synchronized(NewsLocalDataSource::javaClass) {
                    INSTANCE = NewsLocalDataSource(appExecutors, newsDao)
                }
            }
            return INSTANCE!!
        }

    }

    override fun loadAllNews(loadAllNewsCallback: NewsDataSource.LoadAllNewsCallback) {
        launch(appExecutors.diskIO()) {
            val result = newsDao.getAll()
            if (result.isNotEmpty()) {
                launch(appExecutors.mainThread()) {
                    loadAllNewsCallback.onLoaded(NewsFromEntityConverter.convertList(result))
                }
            } else {
                launch(appExecutors.mainThread()) { loadAllNewsCallback.onError() }
            }
        }
    }

    override fun loadNews(id: String, loadNewsCallback: NewsDataSource.LoadNewsCallback) {
        launch(appExecutors.diskIO()) {
            val result = newsDao.get(id)
            if (result != null) {
                launch(appExecutors.mainThread()) {
                    loadNewsCallback.onLoaded(NewsFromEntityConverter.convert(result))
                }
            } else {
                launch(appExecutors.mainThread()) { loadNewsCallback.onError() }
            }
        }
    }

    override fun save(new: News) {
        launch(appExecutors.diskIO()) {
            var old = NewsFromEntityConverter.convertSafe(newsDao.get(new.id!!))
            if (NewsUpdateRule.isNeedUpdate(old, new)) {
                newsDao.insert(NewsToEntityConverter.convert(new))
            }
        }
    }

    override fun refresh() {

    }
}