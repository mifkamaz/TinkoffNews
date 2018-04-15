package ru.mifkamaz.tinkoffnews.data.news.source.remote

import java.util.*


data class Response<out T>(
        private val resultCode: String?,
        val payload: T?,
        val trackingId: String?
) {
    companion object {
        private const val RESULT_CODE_OK = "OK"
    }

    fun isOk(): Boolean = RESULT_CODE_OK == resultCode
}

data class NewsDto(
        val title: NewsTitleDto?,
        val creationDate: DateDto?,
        val lastModificationDate: DateDto?,
        val content: String?,
        val bankInfoTypeId: Int?,
        val typeId: String?
)

data class NewsTitleDto(
        val id: String,
        val name: String?,
        val text: String?,
        val publicationDate: DateDto?,
        val bankInfoTypeId: Int?
)

data class DateDto(val milliseconds: Long) {
    fun toCalendar(): Calendar = Calendar.getInstance().apply { timeInMillis = milliseconds }
}

