package com.sjarifhd.realworldandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.android.synthetic.main.activity_list_post.*

class ListPostActivity : AppCompatActivity() {

    private lateinit var postItemAdapter: ItemAdapter<PostItem>
    private lateinit var fastAdapter: FastAdapter<PostItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_post)

        setPostAdapter()
        getPosts(dummyPosts)
        fabAddPost.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }
    }

    private fun setPostAdapter() {
        postItemAdapter = items()
        fastAdapter = FastAdapter.with(postItemAdapter)

        fastAdapter.onClickListener = { view, _, item, _ ->
            view?.let { onPostClicked(item) }
            false
        }

        rvPost.layoutManager = LinearLayoutManager(this)
        rvPost.setHasFixedSize(false)
        rvPost.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvPost.adapter = fastAdapter
    }

    private fun getPosts(postItems: List<PostItem>) {
        val diffCallback = DiffUtilPostItem()
        val result = FastAdapterDiffUtil.calculateDiff(postItemAdapter, postItems, diffCallback)
        FastAdapterDiffUtil[postItemAdapter] = result
    }

    private fun onPostClicked(postItem: PostItem) {
        startActivity(Intent(this, DetailPostActivity::class.java))
    }
}
