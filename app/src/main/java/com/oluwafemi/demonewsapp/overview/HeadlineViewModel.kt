package com.oluwafemi.demonewsapp.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oluwafemi.demonewsapp.model.Article
import com.oluwafemi.demonewsapp.network.NewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HeadlineViewModel : ViewModel() {
    private val job = Job()

    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private val _newsItems = MutableLiveData<List<Article>>()

    val newsItems: LiveData<List<Article>>
        get() = _newsItems

    init {
        displayNews()
    }

    private fun displayNews() {
        coroutineScope.launch {
            val getNews = NewsAPI.retrofitService.getHeadLinesAsync()

            try {
                val listResult = getNews.await()
                _newsItems.value = listResult.getArticle
                Log.i("JSON", "RESULT: ${_newsItems.value}")

            } catch (e: Exception) {
                Log.i("JSON", "EXCEPTION: $e")
            }
        }
    }
}