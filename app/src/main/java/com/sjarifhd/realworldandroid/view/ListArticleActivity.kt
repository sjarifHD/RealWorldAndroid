package com.sjarifhd.realworldandroid.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.sjarifhd.realworldandroid.R
import kotlinx.android.synthetic.main.activity_list_article.*

class ListArticleActivity : AppCompatActivity() {

    private lateinit var articleItemAdapter: ItemAdapter<ArticleItem>
    private lateinit var fastAdapter: FastAdapter<ArticleItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_article)

        setPostAdapter()
        getPosts(dummyPosts)
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

    private fun getPosts(articleItems: List<ArticleItem>) {
        val diffCallback = DiffUtilArticleItem()
        val result = FastAdapterDiffUtil.calculateDiff(articleItemAdapter, articleItems, diffCallback)
        FastAdapterDiffUtil[articleItemAdapter] = result
    }

    private fun onPostClicked(articleItem: ArticleItem) {
        startActivity(Intent(this, DetailArticleActivity::class.java))
    }
}
