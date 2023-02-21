package com.aya.digital.core.ui.base.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

//Исправлен краш при мультитаче
class ViewPagerFixed : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onTouchEvent(ev)
        } catch (ex: java.lang.IllegalArgumentException) {
            ex.printStackTrace()
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onInterceptTouchEvent(ev)
        } catch (ex: java.lang.IllegalArgumentException) {
            ex.printStackTrace()
        }
        return false
    }
}