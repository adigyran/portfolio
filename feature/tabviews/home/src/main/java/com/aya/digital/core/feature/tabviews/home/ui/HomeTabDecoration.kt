package com.aya.digital.core.feature.tabviews.home.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.dpToPx
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeButtonItemDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeClinicsItemDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesBottomDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesItemDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesTitleDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeLastUpdatesTopDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeNewsContainerDelegate
import com.aya.digital.core.ui.delegates.home.homeitems.ui.HomeSection
import com.aya.digital.core.ui.delegates.home.homeitems.ui.SectionHolder


internal class HomeTabDecoration(private val context: Context) :
    RecyclerView.ItemDecoration() {

    val BUTTONS_COUNT = 7
    private var decoratedLeft = 0.0F
    private var decoratedTop = 0.0F
    private var decoratedRight = 0.0F
    private var decoratedBottom = 0.0F
    private var left: Float = 0.0F
    private var top = 0F
    private var right = 0F
    private var bottom = 0F
    private val paint = Paint()
    private val buttonsColor = context.getColor(R.color.bg_light_blue)
    private val newsColor = context.getColor(R.color.bg_transparent)
    private val otherColor = context.getColor(R.color.bg_transparent)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        val position = parent.getChildAdapterPosition(view)
        if(position == RecyclerView.NO_POSITION) return
        val column: Int = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        val horizontal = (12).dpToPx()
        when (viewHolder) {
            is HomeButtonItemDelegate.ViewHolder -> {

                val spacing = when (column) {
                    0 -> HorizontalSpacings(8, 5)
                    else -> HorizontalSpacings(5, 8)
                }
                outRect.right = spacing.right.dpToPx()
                outRect.left = spacing.left.dpToPx()
            }

            is HomeLastUpdatesTopDelegate.ViewHolder,
            is HomeLastUpdatesBottomDelegate.ViewHolder,
            is HomeLastUpdatesTitleDelegate.ViewHolder,
            is HomeLastUpdatesItemDelegate.ViewHolder -> {
                outRect.left = (20).dpToPx()
                outRect.right = (20).dpToPx()
            }
            is HomeClinicsItemDelegate.ViewHolder -> {
                outRect.left = (20).dpToPx()
                outRect.right = (20).dpToPx()
            }
            else -> {
                outRect.left = (0).dpToPx()
                outRect.right = (0).dpToPx()
            }

        }
        val top = when (viewHolder) {
            is HomeButtonItemDelegate.ViewHolder -> 10
            is HomeLastUpdatesTopDelegate.ViewHolder -> 16
            is HomeLastUpdatesTitleDelegate.ViewHolder -> 0
            is HomeLastUpdatesItemDelegate.ViewHolder -> 0
            is HomeClinicsItemDelegate.ViewHolder -> 24
            else -> 0
        }
        outRect.top = top.dpToPx()
        val bottom = when (viewHolder) {
            is HomeButtonItemDelegate.ViewHolder -> if(position==BUTTONS_COUNT-1) 24 else 0
            else -> 0
        }
        outRect.bottom = bottom.dpToPx()
    }

    private data class HorizontalSpacings(val left: Int, val right: Int)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount;
        var currentSection:HomeSection? = HomeSection.Other
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 2F
        val lm = parent.layoutManager ?: return
        parent.children
            .forEach { view ->
                val viewHolder = parent.findContainingViewHolder(view)
                currentSection = (viewHolder as? SectionHolder)?.getHomeSection()
                currentSection?.let {
                    paint.color = when (currentSection) {
                        HomeSection.Buttons -> buttonsColor
                        HomeSection.News -> newsColor
                        HomeSection.Other -> otherColor
                        else -> otherColor
                    }
                    decoratedLeft = 0f
                    decoratedRight = parent.width.toFloat()
                    decoratedTop = lm.getDecoratedTop(view) + view.translationY
                    decoratedBottom = lm.getDecoratedBottom(view) + view.translationY

                    c.drawRect(decoratedLeft, decoratedTop, decoratedRight, decoratedBottom, paint)
                }

            }

    }
}