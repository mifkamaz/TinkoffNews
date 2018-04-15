package ru.mifkamaz.tinkoffnews.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import ru.mifkamaz.tinkoffnews.DefaultActivityUi
import ru.mifkamaz.tinkoffnews.NewsDataSourceInjection
import ru.mifkamaz.tinkoffnews.utlis.replaceFragment
import timber.log.Timber

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        var defaultActivityUi = DefaultActivityUi()
        defaultActivityUi.setContentView(this);

        var view = supportFragmentManager.findFragmentById(DefaultActivityUi.FRAME_ID)
                as NewsView? ?: NewsView.newInstance().also {
            replaceFragment(it, DefaultActivityUi.FRAME_ID)
        }

        NewsPresenter(NewsDataSourceInjection.provide(this), view, NewsRouter(this))
    }

}
