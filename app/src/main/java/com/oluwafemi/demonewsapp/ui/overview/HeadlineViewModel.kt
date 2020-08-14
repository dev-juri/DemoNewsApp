package com.oluwafemi.demonewsapp.ui.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oluwafemi.demonewsapp.database.getDatabase
import com.oluwafemi.demonewsapp.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HeadlineViewModel(application: Application) : AndroidViewModel(application) {
    private val job = SupervisorJob()

    private val viewModelScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private val database = getDatabase(application)
    private val newsRepository = NewsRepository(database)

    val newsItems = newsRepository.news

    init {
        viewModelScope.launch {
            newsRepository.refreshNews()
        }
    }
    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HeadlineViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HeadlineViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}