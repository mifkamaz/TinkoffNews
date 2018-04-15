package ru.mifkamaz.tinkoffnews.data.news.source

import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.utlis.UpdateRule

object NewsUpdateRule : UpdateRule<News?> {

    override fun isNeedUpdate(old: News?, new: News?) = when {
        new == null -> false
        old == null -> true
        new.lastModificationDate == null -> false
        old.lastModificationDate == null -> true
        old.lastModificationDate.before(new.lastModificationDate) -> true
        else -> false
    }
}