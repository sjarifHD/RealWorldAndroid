package com.sjarifhd.realworldandroid.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjarifhd.realworldandroid.data.local.ArticleDao
import com.sjarifhd.realworldandroid.data.local.ArticleEntity
import com.sjarifhd.realworldandroid.data.network.ArticleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*

class ArticleViewModel(
    private val articleApi: ArticleApi,
    private val articleDao: ArticleDao
) : ViewModel() {

    private var articleLiveData: MutableLiveData<List<ArticleItem>> = MutableLiveData()

    fun getNetwork(): LiveData<List<ArticleItem>> {
        viewModelScope.launch {
            try {
                val res: List<ArticleItem> = withContext(Dispatchers.IO) {
                    val networkResult = articleApi.getGlobal()

                    val mapToEntity = networkResult.articles
                        .map {
                            ArticleEntity(
                                UUID.randomUUID().toString(),
                                it.slug,
                                it.title,
                                it.description,
                                it.body,
                                it.createdAt,
                                it.updatedAt,
                                it.favorited,
                                it.favoritesCount,
                                it.author
                            )
                        }
                    mapToEntity.forEach { articleDao.insert(it) }

                    val mapToView = mapToEntity.map {
                        ArticleItem(
                            UUID.fromString(it.uuid),
                            it.title,
                            it.author.username,
                            it.createdAt,
                            it.favorited
                        )
                    }

                    mapToView
                }

                articleLiveData.postValue(res)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        return articleLiveData
    }

    fun getDatabase(): LiveData<List<ArticleItem>> {
        viewModelScope.launch {
            val res: List<ArticleItem> = withContext(Dispatchers.IO) {
                articleDao.getAll()
                    .map {
                        ArticleItem(
                            UUID.randomUUID(),
                            it.title,
                            it.author.username,
                            it.createdAt,
                            it.favorited
                        )
                    }
            }
            articleLiveData.postValue(res)
        }
        getNetwork()

        return articleLiveData
    }
}

