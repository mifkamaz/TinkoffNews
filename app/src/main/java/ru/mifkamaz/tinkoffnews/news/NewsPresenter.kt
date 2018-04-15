package ru.mifkamaz.tinkoffnews.news

import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSource

class NewsPresenter(val dataSource: NewsDataSource,
                    val view: NewsContract.View,
                    override val router: NewsContract.Router) : NewsContract.Presenter {

    init {
        view.presenter = this
    }

    override fun refresh() {
        refresh(true)
    }

    override fun openTaskDetails(news: News) {
        router.openNewsDetails(news)
    }

    override fun start() {
        refresh(true)
    }

    private fun refresh(force: Boolean) {
        if (!force) {
            view.isActive?.setLoadingIndicator(true)
        }

        if (force) {
            dataSource.refresh()
        }

        dataSource.loadAllNews(object : NewsDataSource.LoadAllNewsCallback {

            override fun onLoaded(newsList: List<News>) {
                view.isActive?.setLoadingIndicator(false)
                processNews(newsList)
            }

            override fun onError() {
                view.isActive?.setLoadingIndicator(false)
                view.isActive?.showLoadingError()
            }
        })
    }

    private fun processNews(newsList: List<News>) {
        view.isActive?.showNews(newsList.sortedByDescending { it.publicationDate })
    }
}