package ru.mifkamaz.tinkoffnews.news

import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import ru.mifkamaz.tinkoffnews.R
import ru.mifkamaz.tinkoffnews.utlis.setDrawable

class NewsViewUi : AnkoComponent<Fragment> {

    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var textEmpty: TextView

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        frameLayout {
            swipeRefreshLayout = swipeRefreshLayout() {
                recyclerView = recyclerView() {
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                            .setDrawable(context, R.drawable.divider_gray_left_medium))
                }
            }
            textEmpty = textView("Нечего показать") {
                textSizeDimen = R.dimen.text_size_large
            }.lparams(gravity = Gravity.CENTER)
        }
    }
}