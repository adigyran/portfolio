package com.aya.digital.core.uibase.utils

import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class NestedViewScrollFixTouchListener(private val scrollOrientation: NestedScrollOrientation) :
    View.OnTouchListener {
    private var preX = 0f
    private var preY = 0f

    private var isParentBlocked = false

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> v.parent.requestDisallowInterceptTouchEvent(true)
            MotionEvent.ACTION_MOVE -> {
                when {
                    scrollOrientation == NestedScrollOrientation.VERTICAL && abs(
                        event.y - preY
                    ) > abs(event.x - preX) -> {
                        v.parent.requestDisallowInterceptTouchEvent(true)
                        isParentBlocked = true
                    }
                    scrollOrientation == NestedScrollOrientation.HORIZONTAL && abs(
                        event.x - preX
                    ) > abs(event.y - preY) -> {
                        v.parent.requestDisallowInterceptTouchEvent(true)
                        isParentBlocked = true
                    }
                    !isParentBlocked -> v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            MotionEvent.ACTION_UP -> {
                v.parent.requestDisallowInterceptTouchEvent(false)
                isParentBlocked = false
            }
        }
        preX = event.x
        preY = event.y
        return false
    }

    enum class NestedScrollOrientation { VERTICAL, HORIZONTAL }
}