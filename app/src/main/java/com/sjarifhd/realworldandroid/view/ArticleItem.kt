package com.sjarifhd.realworldandroid.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.sjarifhd.realworldandroid.R
import kotlinx.android.synthetic.main.item_article.view.*
import java.util.*

open class ArticleItem(
    val uuid: UUID,
    val title: String,
    val author: String,
    val createdAt: String,
    val isFavorite: Boolean
) : AbstractItem<ArticleItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_article

    override val type: Int
        get() = R.id.layout_item_post

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun toString(): String {
        return "PostItem(uuid=$uuid, title='$title', author='$author', createdAt='$createdAt', isFavorite=$isFavorite)"
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<ArticleItem>(view) {
        var titleView: TextView = view.tvTitle
        var authorView: TextView = view.tvAuthor
        var createdAtView: TextView = view.tvCreatedAt
        var isFavoriteView: ImageView = view.imgFavorite

        override fun bindView(item: ArticleItem, payloads: MutableList<Any>) {
            titleView.text = item.title
            authorView.text = item.author
            createdAtView.text = item.createdAt

            val imgRes: Int = if (item.isFavorite) {
                R.drawable.ic_bookmark_grey_24dp
            } else {
                R.drawable.ic_bookmark_border_grey_24dp
            }

            isFavoriteView.setImageResource(imgRes)
        }

        override fun unbindView(item: ArticleItem) {
            titleView.text = null
            authorView.text = null
            createdAtView.text = null
            isFavoriteView.setImageDrawable(null)
        }
    }
}