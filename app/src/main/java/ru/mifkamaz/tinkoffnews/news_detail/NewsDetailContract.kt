package ru.mifkamaz.tinkoffnews.news_detail

import android.content.Context
import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView
import ru.mifkamaz.tinkoffnews.BaseRouter
import ru.mifkamaz.tinkoffnews.data.news.News

interface NewsDetailContract {

    interface View : BaseView<Presenter> {

        val isActive: View?

        fun setLoadingIndicator(visible: Boolean)

        fun showNews(news: News)

        fun showLoadingError();

    }

    interface Presenter : BasePresenter<Router> {

        fun refresh()

    }

    abstract class Router(context: Context) : BaseRouter(context)

}