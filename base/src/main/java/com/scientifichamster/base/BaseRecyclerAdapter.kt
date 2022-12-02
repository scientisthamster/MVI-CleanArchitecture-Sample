package com.scientifichamster.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

/**
 * Base Adapter class for [RecyclerView].
 */
abstract class BaseRecyclerAdapter<M : Any, VB : ViewBinding, VH : BaseViewHolder<M, VB>>(callback: DiffUtil.ItemCallback<M>) :
    ListAdapter<M, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings(getItem(position))
        holder.bind()
    }

    override fun submitList(items: MutableList<M>?) {
        super.submitList(items ?: emptyList())
    }
}