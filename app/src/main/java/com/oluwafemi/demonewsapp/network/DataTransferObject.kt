package com.oluwafemi.demonewsapp.network

import com.oluwafemi.demonewsapp.database.DatabaseArticle
import com.oluwafemi.demonewsapp.model.Source
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkNews(
    val articles: List<NetworkArticle>,
    val status: String,
    val totalResults: Int
)

data class NetworkArticle(
    val author: String ?,
    val content: String ?,
    val description: String ?,
    val publishedAt: String ?,
    val source: Source?,
    val title: String ?,
    val url: String,
    val urlToImage: String ?
)

fun NetworkNews.asDomainModel() : Array<DatabaseArticle> {
    return articles.map {
        DatabaseArticle(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage
        )
    }.toTypedArray()
}