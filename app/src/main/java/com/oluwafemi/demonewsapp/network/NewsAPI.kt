package com.oluwafemi.demonewsapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oluwafemi.demonewsapp.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// https://newsapi.org/v2/top-headlines?country=us&apiKey=c5f0ab174f8d49b4989e405d8e97a9c5
private const val BASE_URL = "https://newsapi.org"

private const val API_KEY = "c5f0ab174f8d49b4989e405d8e97a9c5"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface NewsAPIService {
    @GET("/v2/top-headlines")
    fun getHeadLinesAsync(
        @Query("country")country : String = "us",
        @Query("apiKey")apikey : String = API_KEY
    ) : Deferred<News>
}


object NewsAPI {
    val retrofitService : NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}