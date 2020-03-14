package com.sjarifhd.realworldandroid.data.network

import com.sjarifhd.realworldandroid.model.Article
import com.squareup.moshi.JsonClass
import retrofit2.http.GET

@JsonClass(generateAdapter = true)
data class Articles(val articles: List<Article>, val articlesCount: Int)

interface ArticleApi {
    @GET("articles")
    suspend fun getGlobal(): Articles
}
