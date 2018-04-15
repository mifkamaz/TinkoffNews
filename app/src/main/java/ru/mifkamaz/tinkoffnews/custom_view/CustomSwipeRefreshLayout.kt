package ru.mifkamaz.tinkoffnews.custom_view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.view.View

class CustomSwipeRefreshLayout @JvmOverloads constructor(context: Context,
                                                         attrs: AttributeSet? = null)
    : SwipeRefreshLayout(context, attrs) {

    var scrollUpChild: View? = null

    override fun canChildScrollUp() =
            scrollUpChild?.canScrollVertically(-1) ?: super.canChildScrollUp()
}
