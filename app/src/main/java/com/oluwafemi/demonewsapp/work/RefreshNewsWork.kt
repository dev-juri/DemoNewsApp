package com.oluwafemi.demonewsapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.oluwafemi.demonewsapp.database.getDatabase
import com.oluwafemi.demonewsapp.repository.NewsRepository
import retrofit2.HttpException

class RefreshNewsWork (context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    companion object {
        const val WORK_NAME = "RefreshNewsWorker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = NewsRepository(database)
        return try {
            repository.refreshNews()
            Result.success()
        } catch (e : HttpException) {
            Result.retry()
        }
    }

}