package ru.mifkamaz.tinkoffnews.news

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import ru.mifkamaz.tinkoffnews.data.news.News
import java.text.SimpleDateFormat

class NewsAdapterViewHolder(parent: ViewGroup,
                            private val ui: NewsAdapterUi,
                            private val listener: OnNewsClickListener) :
        RecyclerView.ViewHolder(
                ui.createView(AnkoContext.Companion.create(parent.context, parent, false))) {

    init {
        itemView.setOnClickListener {listener.onNewsClick(news!!)}
    }

    private var news: News? = null

    fun bind(news: News) {
        this.news = news
        ui.textTitle.text = if (news.title != null) Html.fromHtml(news.title) else "-"
        ui.textSecond.text = if (news.publicationDate == null) "-" else
            SimpleDateFormat.getDateTimeInstance().format(news.publicationDate.time)
    }

    interface OnNewsClickListener {

        fun onNewsClick(news: News);

    }

}