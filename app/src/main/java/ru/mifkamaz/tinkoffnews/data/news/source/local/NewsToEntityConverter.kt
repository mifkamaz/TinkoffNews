package ru.mifkamaz.tinkoffnews.data.news.source.local

import ru.mifkamaz.tinkoffnews.data.news.News
import ru.mifkamaz.tinkoffnews.utlis.Converter

object NewsToEntityConverter : Converter<News?, NewsEntity?> {

    override fun convert(i: News?) = NewsEntity(
            i?.id!!,
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