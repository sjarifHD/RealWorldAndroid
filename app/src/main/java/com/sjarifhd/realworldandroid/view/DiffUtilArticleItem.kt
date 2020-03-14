package com.sjarifhd.realworldandroid.view

import com.mikepenz.fastadapter.diff.DiffCallback

class DiffUtilArticleItem : DiffCallback<ArticleItem> {

    override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun getChangePayload(
        oldItem: ArticleItem,
        oldItemPosition: Int,
        newItem: ArticleItem,
        newItemPosition: Int
    ): Any? {
        return null
    }
}