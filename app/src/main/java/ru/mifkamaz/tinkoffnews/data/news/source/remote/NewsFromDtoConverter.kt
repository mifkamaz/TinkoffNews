package ru.mifkamaz.tinkoffnews.data.news.source.remote

import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.utlis.Converter

object NewsFromDtoConverter : Converter<NewsDto?, News?> {

    override fun convert(i: NewsDto?) = News(
            i?.title!!.id,
            i.title.name,
            i.title.text,
            i.creationDate?.toCalendar(),
            i.title.publicationDate?.toCalendar(),
            i.lastModificationDate?.toCalendar(),
            i.content,
            i.bankInfoTypeId,
            i.typeId
    )

}