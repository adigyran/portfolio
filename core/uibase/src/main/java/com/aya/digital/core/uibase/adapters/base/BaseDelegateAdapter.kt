package com.aya.digital.core.uibase.adapters.base

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class BaseDelegateAdapter(delegatesManager: AdapterDelegatesManager<List<DiffItem>>) :
    AsyncListDifferDelegationAdapter<DiffItem>(BaseDiffCallback(), delegatesManager) {

    companion object {
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder private constructor() {
        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        private val delegates: ArrayList<AdapterDelegate<List<DiffItem>>> = ArrayList()

        fun delegate(init: Builder.() -> AdapterDelegate<List<DiffItem>>) =
            apply { delegates.add(init()) }

        fun loadMoreDelegate(init: Builder.() -> AdapterDelegate<List<DiffItem>>) =
            apply { delegates.add(init()) }

        fun build(): BaseDelegateAdapter {
            require(delegates.isNotEmpty()) { "Register at least one adapter" }

            val delegatesManager = AdapterDelegatesManager<List<DiffItem>>()
            delegates.forEach { delegatesManager.addDelegate(it) }

            val adapter =
                BaseDelegateAdapter(delegatesManager)
            delegates.forEach { adapter.delegatesManager.addDelegate(it) }

            return adapter
        }
    }
}