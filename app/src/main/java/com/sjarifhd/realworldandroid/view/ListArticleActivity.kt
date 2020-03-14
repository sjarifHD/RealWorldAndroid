package com.sjarifhd.realworldandroid.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.sjarifhd.realworldandroid.R
import com.sjarifhd.realworldandroid.data.local.RealWorldDatabase
import com.sjarifhd.realworldandroid.data.network.ApiServiceFactory
import kotlinx.android.synthetic.main.activity_list_article.*

class ListArticleActivity : AppCompatActivity() {

    private lateinit var articleItemAdapter: ItemAdapter<ArticleItem>
    private lateinit var fastAdapter: FastAdapter<ArticleItem>

    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_article)

        articleViewModel = ArticleViewModel(
            ApiServiceFactory.articleApi,
            RealWorldDatabase.getDatabase(applicationContext).articleDao()
        )

        setPostAdapter()

        getPosts()

        fabAddPost.setOnClickListener {
            startActivity(Intent(this, AddArticleActivity::class.java))
        }
    }

    private fun setPostAdapter() {
        articleItemAdapter = items()
        fastAdapter = FastAdapter.with(articleItemAdapter)

        fastAdapter.onClickListener = { view, _, item, _ ->
            view?.let { onPostClicked(item) }
            false
        }

        rvPost.layoutManager = LinearLayoutManager(this)
        rvPost.setHasFixedSize(false)
        rvPost.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvPost.adapter = fastAdapter
    }

    private fun getPosts() {
        articleViewModel.getDatabase().observe(this, Observer {
            updatePosts(it)
        })
    }

    private fun updatePosts(articleItems: List<ArticleItem>) {
        val diffCallback = DiffUtilArticleItem()
        val result = FastAdapterDiffUtil.calculateDiff(articleItemAdapter, articleItems, diffCallback)
        FastAdapterDiffUtil[articleItemAdapter] = result
    }

    private fun onPostClicked(articleItem: ArticleItem) {
        startActivity(Intent(this, DetailArticleActivity::class.java))
    }
}
