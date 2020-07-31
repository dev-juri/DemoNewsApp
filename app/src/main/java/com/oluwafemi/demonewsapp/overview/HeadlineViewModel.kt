package com.oluwafemi.demonewsapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oluwafemi.demonewsapp.model.News
import com.oluwafemi.demonewsapp.network.NewsAPI
import kotlinx.coroutines.*

class HeadlineViewModel : ViewModel() {
    private val job = Job()

    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    private val _newsItems = MutableLiveData<List<News>>()

    val newsItems: LiveData<List<News>>
        get() = _newsItems

    init {
        displayNews()
    }

    private fun displayNews() {
        coroutineScope.launch {
            val getNews = NewsAPI.retrofitService.getHeadLinesAsync()

            try {
                val listResult = getNews.await()
                _newsItems.value = listResult

            } catch (e: Exception) {
                _newsItems.value = ArrayList()
            }
        }
    }
}