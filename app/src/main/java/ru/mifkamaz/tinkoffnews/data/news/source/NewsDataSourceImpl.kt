package ru.mifkamaz.tinkoffnews.data.news.source

import ru.mifkamaz.tinkoffnews.data.news.News

class NewsDataSourceImpl(
        private val inMemoryDataSource: NewsDataSource,
        private val localDataSource: NewsDataSource,
        private val remoteDataSource: NewsDataSource
) : NewsDataSource {

    companion object {

        private var INSTANCE: NewsDataSourceImpl? = null

        fun getInstance(inMemoryDataSource: NewsDataSource,
                        localDataSource: NewsDataSource,
                        remoteDataSource: NewsDataSource): NewsDataSource {
            if (INSTANCE == null) {
                INSTANCE = NewsDataSourceImpl(inMemoryDataSource, localDataSource, remoteDataSource)
            }
            return INSTANCE!!
        }
    }

    private var refresh: Boolean = false;

    override fun loadAllNews(loadAllNewsCallback: NewsDataSource.LoadAllNewsCallback) {
        if (refresh) {
            loadNewsFromRemote(loadAllNewsCallback)
            return
        }

        inMemoryDataSource.loadAllNews(object : NewsDataSource.LoadAllNewsCallback {
            override fun onLoaded(newsList: List<News>) {
                loadAllNewsCallback.onLoaded(newsList)
            }

            override fun onError() {
                localDataSource.loadAllNews(object : NewsDataSource.LoadAllNewsCallback {
                    override fun onLoaded(newsList: List<News>) {
                        inMemoryDataSource.save(newsList)
                        loadAllNewsCallback.onLoaded(newsList)
                    }

                    override fun onError() {
                        loadNewsFromRemote(loadAllNewsCallback)
                    }
                })
            }
        })
    }

    private fun loadNewsFromRemote(callback: NewsDataSource.LoadAllNewsCallback) {
        remoteDataSource.loadAllNews(object : NewsDataSource.LoadAllNewsCallback {
            override fun onLoaded(newsList: List<News>) {
                saveToInMemoryAndLocal(newsList)
                callback.onLoaded(newsList)
            }

            override fun onError() {
                callback.onError()
                refresh = false
                loadAllNews(callback)
            }
        })
    }

    private fun loadNewsFromRemote(id: String, callback: NewsDataSource.LoadNewsCallback) {
        remoteDataSource.loadNews(id, object : NewsDataSource.LoadNewsCallback {
            override fun onLoaded(news: News) {
                saveToInMemoryAndLocal(news)
                callback.onLoaded(news)
            }

            override fun onError() {
                callback.onError()
            }
        })
    }

    override fun loadNews(id: String, loadNewsCallback: NewsDataSource.LoadNewsCallback) {
        if (refresh) {
            loadNewsFromRemote(id, loadNewsCallback)
            return
        }

        inMemoryDataSource.loadNews(id, object : NewsDataSource.LoadNewsCallback {
            override fun onLoaded(news: News) {
                loadNewsCallback.onLoaded(news)
            }

            override fun onError() {
                localDataSource.loadNews(id, object : NewsDataSource.LoadNewsCallback {
                    override fun onLoaded(news: News) {
                        inMemoryDataSource.save(news)
                        loadNewsCallback.onLoaded(news)
                    }

                    override fun onError() {
                        loadNewsFromRemote(id, loadNewsCallback)
                    }
                })
            }
        })

    }

    override fun save(newsList: List<News>) {
        TODO("not implemented")
    }

    override fun save(news: News) {
        TODO("not implemented")
    }

    fun saveToInMemoryAndLocal(newsList: List<News>) {
        localDataSource.refresh()
        localDataSource.save(newsList)
        inMemoryDataSource.refresh()
        inMemoryDataSource.save(newsList)
        refresh = false;
    }

    fun saveToInMemoryAndLocal(news: News) {
        localDataSource.save(news)
        inMemoryDataSource.save(news)
    }

    override fun refresh() {
        refresh = true
    }
}
