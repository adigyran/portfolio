package com.aya.digital.core.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlin.math.abs

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}


inline fun <reified T : Activity> intentOf(context: Context, vararg params: Pair<String, Any>) =
    Intent(context, T::class.java).apply {
        val bundle = bundleOf(*params)
        this.putExtras(bundle)

    }

inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any>) =
    T::class.java.newInstance().apply {
        val bundle = bundleOf(*params)
        arguments = bundle
    }


inline fun <reified ActivityType : Activity, ParamType : Any> createActivity(
    context: Context,
    param: ParamType,
) =
    intentOf<ActivityType>(context, "param" to param)


inline fun <reified FragmentType : Fragment, ParamType : Any> createFragment(param: ParamType) =
    instanceOf<FragmentType>("param" to param)

fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Boolean.toInt() = if (this) 1 else 0

fun Int.isEven() = this.rem(2) == 0

fun Int.isOdd(length: Int = 1) = this.rem(2) != 0

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

private const val thresholdValue: Double = 0.0001

fun Float.isEqual(number: Float): Boolean = abs(this - number) < thresholdValue

fun Double.isEqual(number: Double): Boolean = abs(this - number) < thresholdValue

interface ContextAware {
    fun getContextAware(): Context
}