package ru.mifkamaz.tinkoffnews.news

import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import ru.mifkamaz.tinkoffnews.R

class NewsAdapterUi : AnkoComponent<ViewGroup> {

    lateinit var textTitle: TextView
    lateinit var textSecond: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            leftPadding = dimen(R.dimen.margin_medium)
            rightPadding = leftPadding
            topPadding = dimen(R.dimen.margin_small)
            bottomPadding = topPadding

            textTitle = textView("Title") {
                textColorResource = android.R.color.black
                textSizeDimen = R.dimen.text_size_large
                bottomPadding = dimen(R.dimen.margin_tiny)
            }
            textSecond = textView("Second") {
                textSizeDimen = R.dimen.text_size_small
            }
        }
    }
}