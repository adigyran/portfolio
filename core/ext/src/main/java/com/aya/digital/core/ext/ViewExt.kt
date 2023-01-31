package com.aya.digital.core.ext

import android.graphics.Rect
import android.os.SystemClock
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.EditText
import androidx.core.graphics.Insets
import androidx.core.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

fun View.invisible() = run { visibility = View.INVISIBLE }

fun View.visible() = run { visibility = View.VISIBLE }
fun View.gone() = run { visibility = View.GONE }

fun View.toggleVisibility(visible: Boolean, visibilityWhenFalse: Int = View.GONE) =
    run { visibility = if (visible) View.VISIBLE else visibilityWhenFalse }

val View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
val View.isVisible: Boolean
    get() = visibility == View.VISIBLE
val View.isGone: Boolean
    get() = visibility == View.GONE

fun View.disable() = run { this.isEnabled = false }
fun View.enable() = run { this.isEnabled = true }

fun View.toggleAvailability(enabled: Boolean) = run { isEnabled = enabled }

fun ViewGroup.disableEnableControls(enable: Boolean) {
    this.isEnabled = enable
    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child is ViewGroup) child.disableEnableControls(enable) else child.isEnabled =
            enable
    }
}

fun View.select() = run { this.isSelected = true }
fun View.unselect() = run { this.isSelected = false }

fun View.toggleSelection(selected: Boolean) = run { isSelected = selected }

fun ViewGroup.selectUnselectControls(isSelected: Boolean) {
    this.isSelected = isSelected
    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child is ViewGroup) child.disableEnableControls(isSelected) else child.isSelected =
            isSelected
    }
}

inline fun View.afterMeasured(crossinline f: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun View.margin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = this }
        top?.run { topMargin = this }
        right?.run { rightMargin = this }
        bottom?.run { bottomMargin = this }
    }

    this.requestLayout()
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

infix fun View.bindClick(action: (v: View) -> Unit) {
    this.clickWithDebounce(action = action)
}

fun View.clickWithDebounce(debounceTime: Long = 1000L, action: (v: View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun EditText.enableVerticalScrollInScrollView() {
    this.setOnTouchListener(object : View.OnTouchListener {
        private var preX = 0f
        private var preY = 0f

        private var isParentBlocked = false

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> v.parent.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_MOVE -> {
                    val isPossiblyScrollVertical = abs(event.y - preY) > abs(event.x - preX)
                    when {
                        isPossiblyScrollVertical -> {
                            val deltaY = preY - event.y
                            when {
                                deltaY > 0 && v.canScrollVertically(1) -> {
                                    v.parent.requestDisallowInterceptTouchEvent(true)
                                    isParentBlocked = true
                                }
                                deltaY < 0 && v.canScrollVertically(-1) -> {
                                    v.parent.requestDisallowInterceptTouchEvent(true)
                                    isParentBlocked = true
                                }
                                !isParentBlocked -> v.parent.requestDisallowInterceptTouchEvent(
                                    false
                                )
                            }
                        }
                        !isParentBlocked -> v.parent.requestDisallowInterceptTouchEvent(
                            false
                        )
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

    })
    this.movementMethod = ScrollingMovementMethod.getInstance()
}

//TODO add


/*fun RecyclerView.fixHorizontalScrollInVerticalContainer() = addOnItemTouchListener(
    NestedRecyclerScrollFixTouchListener(
        NestedRecyclerScrollFixTouchListener.NestedScrollOrientation.HORIZONTAL
    )
)*/

fun RecyclerView.addOnPositionChangeListener(
    listener: (firstVisibleItemPosition: Int, offset: Int) -> Unit,
): RecyclerView.OnScrollListener {
    val result = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val v = recyclerView.getChildAt(0)
                val left =
                    if (v == null) 0 else v.left - recyclerView.paddingStart

                listener(
                    (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                        ?: 0, left
                )
            }
        }
    }

    addOnScrollListener(result)

    return result
}

fun View.doOnApplyWindowInsets(block: (v: View, insets: WindowInsetsCompat, initialPadding: Rect, initialMargin: Rect) -> WindowInsetsCompat) {
    val initialPadding = recordInitialPaddingForView(this)
    val initialMargin = recordInitialMarginForView(this)

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding, initialMargin)
    }

    doOnAttach { it.requestApplyInsets() }
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

private fun recordInitialMarginForView(view: View) =
    Rect(view.marginLeft, view.marginTop, view.marginRight, view.marginBottom)

inline fun Insets.update(
    left: Int = this.left,
    top: Int = this.top,
    right: Int = this.right,
    bottom: Int = this.bottom,
): Insets =
    Insets.of(left, top, right, bottom)