package com.scientifichamster.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Base ViewHolder class for [RecyclerView].
 */
abstract class BaseViewHolder<M, VB : ViewBinding>(binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    private var item: M? = null

    fun doBindings(data: M?) {
        this.item = data
    }

    abstract fun bind()

    fun getRowItem(): M? = item
}