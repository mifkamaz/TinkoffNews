package ru.mifkamaz.tinkoffnews.data.news.source.local

import ru.mifkamaz.tinkoffnews.utlis.Converter
import ru.mifkamaz.tinkoffnews.data.news.News

object NewsFromEntityConverter : Converter<NewsEntity, News> {

    override fun convert(i: NewsEntity): News = News(
            i.id,
            i.name,
            i.title,
            i.creationDate,
            i.publicationDate,
            i.lastModificationDate,
            i.content,
            i.bankInfoTypeId,
            i.typeId
    )

}