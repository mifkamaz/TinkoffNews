package ru.mifkamaz.tinkoffnews.news

import android.content.Context
import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView
import ru.mifkamaz.tinkoffnews.BaseRouter
import ru.mifkamaz.tinkoffnews.data.news.News

interface NewsContract {

    interface View : BaseView<Presenter> {

        val isActive: View?

        fun setLoadingIndicator(visible: Boolean)

        fun showNews(newsList: List<News>)

        fun showLoadingError();

    }

    interface Presenter : BasePresenter<Router> {

        fun refresh()

        fun openTaskDetails(news: News)

    }

    abstract class Router(context: Context) : BaseRouter(context) {

        abstract fun openNewsDetails(news: News)

    }

}