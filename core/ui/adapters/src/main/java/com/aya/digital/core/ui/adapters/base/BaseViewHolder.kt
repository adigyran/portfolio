package com.aya.digital.core.ui.adapters.base

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.ContextAware

open class BaseViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView),
    ContextAware {
    protected lateinit var item: T

    override fun getContextAware(): Context = itemView.context

    @CallSuper
    open fun bind(item: T) {
        this.item = item
    }

    open fun onViewAttachedToWindow() = Unit

    open fun onViewDetachedFromWindow() = Unit

    open fun onViewRecycled() = Unit
}