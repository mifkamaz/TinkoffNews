package ru.mifkamaz.tinkoffnews.utlis

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.ViewManager
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.custom.ankoView
import ru.mifkamaz.tinkoffnews.custom_view.CustomSwipeRefreshLayout
import timber.log.Timber

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun DividerItemDecoration.setDrawable(context: Context, drawable: Int): DividerItemDecoration {
    setDrawable(context.resources.getDrawable(drawable))
    return this
}

suspend fun <T> Deferred<T>.awaitSafe(): T? {
    try {
        return await()
    } catch (e: Exception) {
        Timber.e(e)
        return null
    }
}

inline fun ViewManager.customSwipeRefreshLayout() = customSwipeRefreshLayout() {}

inline fun ViewManager.customSwipeRefreshLayout(init: CustomSwipeRefreshLayout.() -> Unit): CustomSwipeRefreshLayout {
    return ankoView({ CustomSwipeRefreshLayout(it) }, theme = 0, init = init)
}