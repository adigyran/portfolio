package com.aya.digital.core.uibase.adapters.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

abstract class BaseDelegateAdapter2 :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Restorable {
    var items: List<DiffItem>
        get() = adapter.items
        set(value) {
            adapter.items = value
        }

    protected var recyclerScrollPositions: HashMap<String, ScrolledItemPosition> =
        HashMap()
    protected var collapsedItemsPositions: HashMap<String, Boolean> =
        HashMap()

    abstract val adapter: AsyncListDifferDelegationAdapterModified<DiffItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        adapter.onCreateViewHolder(parent, viewType)

    override fun getItemCount(): Int = adapter.itemCount

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        adapter.onBindViewHolder(holder, position)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>,
    ) =
        adapter.onBindViewHolder(holder, position, payloads)

    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) =
        adapter.unregisterAdapterDataObserver(observer)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        adapter.onViewDetachedFromWindow(holder)

    override fun getItemId(position: Int): Long = adapter.getItemId(position)

    override fun setHasStableIds(hasStableIds: Boolean) = adapter.setHasStableIds(hasStableIds)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean =
        adapter.onFailedToRecycleView(holder)

    override fun getItemViewType(position: Int): Int = adapter.getItemViewType(position)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) =
        adapter.onAttachedToRecyclerView(recyclerView)

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) =
        adapter.onDetachedFromRecyclerView(recyclerView)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = adapter.onViewRecycled(holder)

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) =
        adapter.registerAdapterDataObserver(observer)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        adapter.onViewAttachedToWindow(holder)

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(
            RECYCLER_SCROLL_POSITIONS,
            recyclerScrollPositions
        )
        outState.putSerializable(
            COLLAPSED_ITEMS_POSITIONS,
            collapsedItemsPositions
        )
    }

    @CallSuper
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return

        recyclerScrollPositions = (savedInstanceState.getSerializable(
            RECYCLER_SCROLL_POSITIONS
        ) as? HashMap<String, ScrolledItemPosition>)
            ?: HashMap()

        collapsedItemsPositions = (savedInstanceState.getSerializable(
            COLLAPSED_ITEMS_POSITIONS
        ) as? HashMap<String, Boolean>)
            ?: HashMap()
    }

    fun setItems(items: List<DiffItem>, commitCallback: () -> Unit) =
        adapter.setItems(items, commitCallback)

    companion object {
        private const val RECYCLER_SCROLL_POSITIONS: String =
            "RECYCLER_SCROLL_POSITIONS"
        private const val COLLAPSED_ITEMS_POSITIONS: String =
            "COLLAPSED_ITEMS_POSITIONS"
    }

    class AsyncListDifferDelegationAdapterModified<T : Any?> :
        AsyncListDifferDelegationAdapter<T> {
        constructor(
            diffCallback: DiffUtil.ItemCallback<T>,
            delegatesManager: AdapterDelegatesManager<List<T>>,
        ) : super(diffCallback, delegatesManager)

        fun setItems(items: List<T>, commitCallback: () -> Unit) {
            differ.submitList(items, commitCallback)
        }
    }
}

typealias ScrolledItemPosition = Pair<Int, Int>