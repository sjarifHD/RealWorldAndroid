package com.sjarifhd.realworldandroid

import com.mikepenz.fastadapter.diff.DiffCallback

class DiffUtilPostItem : DiffCallback<PostItem> {

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun getChangePayload(
        oldItem: PostItem,
        oldItemPosition: Int,
        newItem: PostItem,
        newItemPosition: Int
    ): Any? {
        return null
    }
}