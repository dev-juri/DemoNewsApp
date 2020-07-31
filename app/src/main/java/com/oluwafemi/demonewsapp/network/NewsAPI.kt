package com.oluwafemi.demonewsapp.network

import androidx.lifecycle.LiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oluwafemi.demonewsapp.model.Article
import com.oluwafemi.demonewsapp.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// https://newsapi.org/v2/top-headlines?country=us&apiKey=c5f0ab174f8d49b4989e405d8e97a9c5
private const val BASE_URL = "https://newsapi.org/v2/"

private const val API_KEY = "c5f0ab174f8d49b4989e405d8e97a9c5"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface NewsAPIService {
    @GET("top-headlines")
    fun getHeadLinesAsync(
        @Query("country")country : String = "ng",
        @Query("key")apiKey : String = API_KEY
    ) : Deferred<List<News>>
}

val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object NewsAPI {
    val retrofitService : NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}