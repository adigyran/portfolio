package com.aya.digital.core.ui.base.utils

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class NestedRecyclerScrollFixTouchListener(private val scrollOrientation: NestedScrollOrientation) :
    RecyclerView.OnItemTouchListener {
    private var preX = 0f
    private var preY = 0f

    private var isParentBlocked = false

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) = Unit

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) = Unit

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> rv.parent.requestDisallowInterceptTouchEvent(true)
            MotionEvent.ACTION_MOVE -> {
                when {
                    scrollOrientation == NestedScrollOrientation.VERTICAL && abs(e.y - preY) > abs(
                        e.x - preX
                    ) -> {
                        rv.parent.requestDisallowInterceptTouchEvent(true)
                        isParentBlocked = true
                    }
                    scrollOrientation == NestedScrollOrientation.HORIZONTAL && abs(
                        e.x - preX
                    ) > abs(e.y - preY) -> {
                        rv.parent.requestDisallowInterceptTouchEvent(true)
                        isParentBlocked = true
                    }
                    !isParentBlocked -> rv.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            MotionEvent.ACTION_UP -> {
                rv.parent.requestDisallowInterceptTouchEvent(false)
                isParentBlocked = false
            }
        }
        preX = e.x
        preY = e.y
        return false
    }

    enum class NestedScrollOrientation { VERTICAL, HORIZONTAL }
}