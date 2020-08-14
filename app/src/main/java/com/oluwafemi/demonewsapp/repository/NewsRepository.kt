package com.oluwafemi.demonewsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.oluwafemi.demonewsapp.database.NewsDatabase
import com.oluwafemi.demonewsapp.database.asDomainModel
import com.oluwafemi.demonewsapp.model.Article
import com.oluwafemi.demonewsapp.network.NewsAPI
import com.oluwafemi.demonewsapp.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val newsDatabase: NewsDatabase) {
    val news : LiveData<List<Article>> = Transformations.map(newsDatabase.newsDao.getAllArticles()){
        it.asDomainModel()
    }
    suspend fun refreshNews() {
        withContext(Dispatchers.IO) {
            val news = NewsAPI.retrofitService.getHeadLinesAsync().await()
            newsDatabase.newsDao.insertArticles(*news.asDomainModel())
        }
    }
}