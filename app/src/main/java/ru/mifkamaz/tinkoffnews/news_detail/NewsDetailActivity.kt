package ru.mifkamaz.tinkoffnews.news_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import org.jetbrains.anko.setContentView
import ru.mifkamaz.tinkoffnews.DefaultActivityUi
import ru.mifkamaz.tinkoffnews.NewsDataSourceInjection
import ru.mifkamaz.tinkoffnews.utlis.replaceFragment

class NewsDetailActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_NEWS_ID = "extra_news_id"

        @JvmStatic
        fun newInstance(context: Context, newsId: String) =
                Intent(context, NewsDetailActivity::class.java)
                        .putExtra(EXTRA_NEWS_ID, newsId)

    }

    private fun getNewsId() = intent.getStringExtra(EXTRA_NEWS_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val defaultActivityUi = DefaultActivityUi()
        defaultActivityUi.setContentView(this);

        val view = supportFragmentManager.findFragmentById(DefaultActivityUi.FRAME_ID)
                as NewsDetailView? ?: NewsDetailView.newInstance().also {
            replaceFragment(it, DefaultActivityUi.FRAME_ID)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        NewsDetailPresenter(getNewsId(), NewsDataSourceInjection.provide(this), view,
                NewsDetailRouter(this))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true;
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}
