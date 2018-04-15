package ru.mifkamaz.tinkoffnews.news_detail

import android.support.v4.app.Fragment
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView
import ru.mifkamaz.tinkoffnews.R
import ru.mifkamaz.tinkoffnews.custom_view.CustomSwipeRefreshLayout
import ru.mifkamaz.tinkoffnews.utlis.customSwipeRefreshLayout

class NewsDetailViewUi : AnkoComponent<Fragment> {

    lateinit var swipeRefreshLayout: CustomSwipeRefreshLayout
    lateinit var textTitle: TextView
    lateinit var textDate: TextView
    lateinit var textContent: TextView

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        frameLayout {
            swipeRefreshLayout = customSwipeRefreshLayout {
                nestedScrollView {
                    verticalLayout {
                        padding = dimen(R.dimen.margin_medium)
                        textTitle = textView("Title") {
                            textColorResource = android.R.color.black
                            textSizeDimen = R.dimen.text_size_large
                            bottomPadding = dimen(R.dimen.margin_tiny)
                        }
                        textDate = textView("Date") {
                            textSizeDimen = R.dimen.text_size_small
                            bottomPadding = dimen(R.dimen.margin_tiny)
                        }
                        textContent = textView("Content") {
                            textColorResource = android.R.color.black
                            textSizeDimen = R.dimen.text_size_medium
                            bottomPadding = dimen(R.dimen.margin_tiny)
                        }
                    }
                }
            }
        }
    }
}