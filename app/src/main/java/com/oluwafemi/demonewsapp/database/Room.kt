package com.oluwafemi.demonewsapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(vararg article: DatabaseArticle)

    @Query("select * from databasearticle")
    fun getAllArticles() : LiveData<List<DatabaseArticle>>
}

@Database(entities = [DatabaseArticle::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao : NewsDao
}

private lateinit var INSTANCE : NewsDatabase

fun getDatabase(context: Context) : NewsDatabase {
    synchronized(NewsDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "News"
            ).build()
        }
        return INSTANCE
    }
}