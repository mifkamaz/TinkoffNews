package ru.mifkamaz.tinkoffnews.news_detail

import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.data.news.source.NewsDataSource

class NewsDetailPresenter(private val newsId: String,
                          private val dataSource: NewsDataSource,
                          private val view: NewsDetailContract.View,
                          override val router: NewsDetailContract.Router) : NewsDetailContract.Presenter {

    init {
        view.presenter = this
    }

    override fun refresh() {
        refresh(true)
    }

    override fun start() {
        refresh(false)
    }

    private fun refresh(force: Boolean) {
        if (!force) {
            view.isActive?.setLoadingIndicator(true)
        }

        if (force) {
            dataSource.refresh()
        }

        dataSource.loadNews(newsId, object : NewsDataSource.LoadNewsCallback {

            override fun onLoaded(news: News) {
                view.isActive?.setLoadingIndicator(false)
                if (news.lastModificationDate == null) {
                    refresh(true)
                }
                processNews(news)
            }

            override fun onError() {
                view.isActive?.setLoadingIndicator(false)
                view.isActive?.showLoadingError()
            }
        })
    }

    private fun processNews(news: News) {
        view.isActive?.showNews(news)
    }
}