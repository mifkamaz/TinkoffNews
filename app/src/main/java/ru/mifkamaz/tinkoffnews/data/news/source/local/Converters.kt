package ru.mifkamaz.tinkoffnews.data.news.source.local

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun calendarToLong(calendar: Calendar?) = calendar?.timeInMillis

    @TypeConverter
    fun longToCalendar(long: Long?) =
            if (long != null) Calendar.getInstance().apply { timeInMillis = long } else null

}