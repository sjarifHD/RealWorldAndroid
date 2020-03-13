package com.sjarifhd.realworldandroid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

open class PostItem(
    val uuid: UUID,
    val title: String,
    val author: String,
    val createdAt: String,
    val isFavorite: Boolean
) : AbstractItem<PostItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_post

    override val type: Int
        get() = R.id.layout_item_post

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun toString(): String {
        return "PostItem(uuid=$uuid, title='$title', author='$author', createdAt='$createdAt', isFavorite=$isFavorite)"
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<PostItem>(view) {
        var titleView: TextView = view.tvTitle
        var authorView: TextView = view.tvAuthor
        var createdAtView: TextView = view.tvCreatedAt
        var isFavoriteView: ImageView = view.imgFavorite

        override fun bindView(item: PostItem, payloads: MutableList<Any>) {
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

        override fun unbindView(item: PostItem) {
            titleView.text = null
            authorView.text = null
            createdAtView.text = null
            isFavoriteView.setImageDrawable(null)
        }
    }
}