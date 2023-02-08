package com.aya.digital.core.ui.adapters.base

import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

abstract class BaseDelegate<T : DiffItem> :
    AbsListItemAdapterDelegate<T, DiffItem, BaseViewHolder<T>>() {
    override fun onBindViewHolder(item: T, holder: BaseViewHolder<T>, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        (holder as BaseViewHolder<T>).onViewDetachedFromWindow()
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        (holder as BaseViewHolder<T>).onViewAttachedToWindow()
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as BaseViewHolder<T>).onViewRecycled()
    }
}