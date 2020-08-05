package com.oluwafemi.demonewsapp.model


data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {
    val getArticle: List<Article>
        get() = this.articles
}

