package ru.mifkamaz.tinkoffnews.data.news.source.in_memory

import kotlinx.coroutines.experimental.launch
import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSource
import ru.mifkamaz.tinkoffnews.data.news.source.NewsUpdateRule
import ru.mifkamaz.tinkoffnews.utlis.AppExecutors

class NewsInMemoryDataSource private constructor(
        private val appExecutors: AppExecutors
) : NewsDataSource {

    companion object {

        private var INSTANCE: NewsDataSource? = null;

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors): NewsDataSource {
            if (INSTANCE == null) {
                synchronized(NewsInMemoryDataSource::javaClass) {
                    INSTANCE = NewsInMemoryDataSource(appExecutors)
                }
            }
            return INSTANCE!!
        }

    }

    private var cache: LinkedHashMap<String?, News> = LinkedHashMap()

    override fun loadAllNews(loadAllNewsCallback: NewsDataSource.LoadAllNewsCallback) {
        if (cache.isNotEmpty()) {
            launch(appExecutors.mainThread()) {
                loadAllNewsCallback.onLoaded(ArrayList(cache.values))
            }
        } else {
            launch(appExecutors.mainThread()) { loadAllNewsCallback.onError() }
        }
    }

    override fun loadNews(id: String, loadNewsCallback: NewsDataSource.LoadNewsCallback) {
        if (cache.containsKey(id)) {
            launch(appExecutors.mainThread()) {
                loadNewsCallback.onLoaded(cache[id]!!)
            }
        } else {
            launch(appExecutors.mainThread()) { loadNewsCallback.onError() }
        }
    }

    override fun save(news: News) {
        if (NewsUpdateRule.isNeedUpdate(cache[news.id], news)) {
            cache[news.id] = news
        }
    }

    override fun refresh() {
//        cache.clear()
    }
}