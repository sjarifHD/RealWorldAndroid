package com.sjarifhd.realworldandroid.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    val slug: String,
    val title: String,
    var description: String = "",
    var body: String = "",
    var tags: List<String> = listOf(),
    val createdAt: String,
    val updatedAt: String,
    val favorited: Boolean,
    val favoritesCount: Int,
    val author: Author
)

@JsonClass(generateAdapter = true)
data class Author(
    val username: String,
    var bio: String? = "",
    var image: String? = "",
    val following: Boolean
)
