package ru.mifkamaz.tinkoffnews.data.news

import java.util.*

data class News(
        val id: String?,
        val name: String?,
        val title: String?,
        val creationDate: Calendar?,
        val publicationDate: Calendar?,
        val lastModificationDate: Calendar?,
        val content: String?,
        val bankInfoTypeId: Int?,
        val typeId: String?
) {

    override fun hashCode(): Int {
        return id?.hashCode() ?: super.hashCode()
    }

}

