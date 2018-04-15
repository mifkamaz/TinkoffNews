package ru.mifkamaz.tinkoffnews.news_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast
import ru.mifkamaz.tinkoffnews.R
import ru.mifkamaz.tinkoffnews.data.news.News
import java.text.SimpleDateFormat

class NewsDetailView : Fragment(), NewsDetailContract.View {

    private val ui = NewsDetailViewUi()

    companion object {
        @JvmStatic
        fun newInstance() = NewsDetailView()
    }

    override lateinit var presenter: NewsDetailContract.Presenter

    override var isActive: NewsDetailContract.View? = null
        get() = if (isAdded) this else null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return ui.createView(AnkoContext.Companion.create(context, this));
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.swipeRefreshLayout.onRefresh { presenter.refresh() }
        ui.textContent.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun setLoadingIndicator(visible: Boolean) {
        ui.swipeRefreshLayout.isRefreshing = visible
    }

    override fun showNews(news: News) {
        ui.textTitle.text = if (news.title != null) Html.fromHtml(news.title) else "-"
        ui.textDate.text = if (news.publicationDate == null) "-" else
            SimpleDateFormat.getDateTimeInstance().format(news.publicationDate.time)
        ui.textContent.text = if (news.content != null) Html.fromHtml(news.content) else "-"
    }

    override fun showLoadingError() {
        toast(R.string.error_loading)
    }
}