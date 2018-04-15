package ru.mifkamaz.tinkoffnews.data.news.source.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.util.*

@Entity(tableName = "news")
@TypeConverters(Converters::class)
data class NewsEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "title")
        var title: String?,

        @ColumnInfo(name = "creationDate")
        var creationDate: Calendar?,

        @ColumnInfo(name = "publicationDate")
        var publicationDate: Calendar?,

        @ColumnInfo(name = "lastModificationDate")
        var lastModificationDate: Calendar?,

        @ColumnInfo(name = "content")
        var content: String?,

        @ColumnInfo(name = "bankInfoTypeId")
        var bankInfoTypeId: Int?,

        @ColumnInfo(name = "typeId")
        var typeId: String?
)

