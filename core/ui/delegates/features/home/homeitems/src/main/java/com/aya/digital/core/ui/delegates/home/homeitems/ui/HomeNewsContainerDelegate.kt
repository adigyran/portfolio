package com.aya.digital.core.ui.delegates.home.homeitems.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.addOnPositionChangeListener
import com.aya.digital.core.ui.adapters.base.BaseDelegate
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.adapters.base.BaseViewHolder
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.adapters.base.ScrolledItemPosition
import com.aya.digital.core.ui.delegates.home.homeitems.model.HomeNewsContainerUIModel
import com.aya.digital.core.ui.delegates.features.home.homeitems.databinding.ItemHomeNewsContainerBinding

class HomeNewsContainerDelegate(private val recyclerPool: RecyclerView.RecycledViewPool,
                                private val recyclerScrollPositions: () -> MutableMap<String, ScrolledItemPosition>,) :
    BaseDelegate<HomeNewsContainerUIModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean = item is HomeNewsContainerUIModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HomeNewsContainerUIModel> {
        val binding =
            ItemHomeNewsContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemHomeNewsContainerBinding) :
        BaseViewHolder<HomeNewsContainerUIModel>(binding.root),SectionHolder {

        private val lm = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            .apply {
                initialPrefetchItemCount = 3
            }

        private val newsAdapter = BaseDelegateAdapter.create {
           delegate { HomeNewsItemDelegate() }
        }
        private var positionChangeListener: RecyclerView.OnScrollListener? = null

        init {
            with(binding.rvNews)
            {
                itemAnimator = null
                setHasFixedSize(false)
                setItemViewCacheSize(4)
                isNestedScrollingEnabled = false
                setRecycledViewPool(recyclerPool)
                layoutManager = lm
                swapAdapter(newsAdapter, true)
            }
            PagerSnapHelper().attachToRecyclerView(binding.rvNews)

        }
        override fun bind(item: HomeNewsContainerUIModel) {
            super.bind(item)
            newsAdapter.items = item.news
            positionChangeListener?.let { binding.rvNews.removeOnScrollListener(it) }

            val scrolledPosition = recyclerScrollPositions()["HomeNewsContainerDelegate:"]
            if (scrolledPosition == null) {
                lm.scrollToPosition(0)
            } else {
                lm.scrollToPositionWithOffset(scrolledPosition.first, scrolledPosition.second)
            }

            positionChangeListener =
                binding.rvNews.addOnPositionChangeListener { firstVisibleItemPosition, offset ->
                    recyclerScrollPositions()["HomeNewsContainerDelegate"] =
                        ScrolledItemPosition(
                            firstVisibleItemPosition,
                            offset
                        )
                }

        }
        override fun getHomeSection(): HomeSection = HomeSection.News

    }
}