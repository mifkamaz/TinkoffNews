package ru.mifkamaz.tinkoffnews.data.news.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao interface NewsDao {

    @Query("SELECT * FROM News") fun getAll(): List<NewsEntity>

    @Query("SELECT * FROM News WHERE id = :taskId") fun get(taskId: String): NewsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(task: NewsEntity)

    @Query("DELETE FROM News") fun deleteAll()

}