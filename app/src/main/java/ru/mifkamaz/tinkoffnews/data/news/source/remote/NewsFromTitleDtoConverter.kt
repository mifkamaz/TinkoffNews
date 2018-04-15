package ru.mifkamaz.tinkoffnews.data.news.source.remote

import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.utlis.Converter

object NewsFromTitleDtoConverter : Converter<NewsTitleDto, News> {

    override fun convert(i: NewsTitleDto): News = News(
            i.id,
            i.name,
            i.text,
            null,
            i.publicationDate?.toCalendar(),
            null,
            null,
            i.bankInfoTypeId,
            null
    )

}