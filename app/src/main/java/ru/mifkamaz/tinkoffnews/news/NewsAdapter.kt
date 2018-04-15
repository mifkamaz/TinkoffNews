package ru.mifkamaz.tinkoffnews.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.mifkamaz.tinkoffnews.data.news.News

class NewsAdapter(private val listener: NewsAdapterViewHolder.OnNewsClickListener) :
        RecyclerView.Adapter<NewsAdapterViewHolder>() {

    var news: List<News>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            = NewsAdapterViewHolder(parent!!, NewsAdapterUi(), listener)

    override fun getItemCount() = news?.size ?: 0

    override fun onBindViewHolder(holder: NewsAdapterViewHolder?, position: Int) {
        holder?.bind(news?.get(position)!!)
    }

    override fun getItemId(position: Int): Long {
        return news?.get(position)!!.hashCode().toLong()
    }
}