package com.sjarifhd.realworldandroid.data.local

import androidx.room.*
import com.sjarifhd.realworldandroid.model.Author
import java.util.*

@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey val uuid: String,
    val username: String,
    var bio: String? = "",
    var image: String? = "",
    val following: Boolean
)

@Entity(tableName = "articles",  indices = [Index(value = ["slug", "title"], unique = true)])
data class ArticleEntity(
    @PrimaryKey val uuid: String,
    val slug: String,
    val title: String,
    var description: String = "",
    var body: String = "",
    val createdAt: String,
    val updatedAt: String,
    val favorited: Boolean,
    val favoritesCount: Int,
    @Embedded val author: Author
)

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg articles: ArticleEntity)

    @Query("select * from articles")
    suspend fun getAll(): List<ArticleEntity>

    @Query("select * from articles where slug = :slug")
    suspend fun getBySlug(slug: String): ArticleEntity
}