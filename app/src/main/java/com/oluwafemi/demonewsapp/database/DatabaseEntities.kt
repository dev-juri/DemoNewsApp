package com.oluwafemi.demonewsapp.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oluwafemi.demonewsapp.model.Article
import com.oluwafemi.demonewsapp.model.Source


@Entity(tableName = "databasearticle")
data class DatabaseArticle constructor(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?
)


fun List<DatabaseArticle>.asDomainModel(): List<Article> {
    return map {
        Article(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            source = null,
            title = it.title,
            urlToImage = it.urlToImage,
            url = it.url
        )
    }
}