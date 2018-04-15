package ru.mifkamaz.tinkoffnews.data.news.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(NewsEntity::class), version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        private var INSTANCE: NewsDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): NewsDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NewsDatabase::class.java, "News.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}