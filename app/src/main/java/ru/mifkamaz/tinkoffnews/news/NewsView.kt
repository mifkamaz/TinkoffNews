package ru.mifkamaz.tinkoffnews.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast
import ru.mifkamaz.tinkoffnews.R
import ru.mifkamaz.tinkoffnews.data.news.News

class NewsView : Fragment(), NewsContract.View, NewsAdapterViewHolder.OnNewsClickListener {

    private val ui = NewsViewUi()
    private val adapter = NewsAdapter(this)

    companion object {
        val SAVED_STATE_RECYCLER = "recycler"
        @JvmStatic
        fun newInstance() = NewsView()
    }

    override lateinit var presenter: NewsContract.Presenter

    override var isActive: NewsContract.View? = null
        get() = if (isAdded) this else null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return ui.createView(AnkoContext.Companion.create(context, this));
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.swipeRefreshLayout.onRefresh { presenter.refresh() }
        ui.recyclerView.layoutManager
                .onRestoreInstanceState(savedInstanceState?.getParcelable(SAVED_STATE_RECYCLER))
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun setLoadingIndicator(visible: Boolean) {
        ui.swipeRefreshLayout.isRefreshing = visible
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("SAVED_STATE_RECYCLER",
                ui.recyclerView.layoutManager.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }

    override fun showNews(newsList: List<News>) {
        adapter.news = newsList
        var onSaveInstanceState = ui.recyclerView.layoutManager.onSaveInstanceState()
        ui.recyclerView.adapter = adapter
        ui.recyclerView.layoutManager.onRestoreInstanceState(onSaveInstanceState)
        ui.textEmpty.visibility = if (newsList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun showLoadingError() {
        toast(R.string.error_loading)
    }

    override fun onNewsClick(news: News) {
        presenter.openTaskDetails(news)
    }

}