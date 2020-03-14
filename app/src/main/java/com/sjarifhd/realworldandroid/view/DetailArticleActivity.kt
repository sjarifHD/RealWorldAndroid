package com.sjarifhd.realworldandroid.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sjarifhd.realworldandroid.R
import kotlinx.android.synthetic.main.activity_detail_article.*

class DetailArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)

        tvBody.visibility = View.VISIBLE
    }
}
