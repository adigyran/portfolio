package com.aya.digital.core.designsystem.decoration

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.content.res.AppCompatResources
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.dpToPx
import com.google.android.material.datepicker.DayViewDecorator
import java.util.Calendar
import java.util.TimeZone

class CircleIndicatorDayDecorator() : DayViewDecorator() {
    constructor(parcel: Parcel) : this() {
    }


    private lateinit var context: Context

    override fun initialize(context: Context) {
        this.context = context
    }

    override fun getCompoundDrawableTop(
        context: Context,
        year: Int,
        month: Int,
        day: Int,
        valid: Boolean,
        selected: Boolean
    ): Drawable? = getTopSpacer()

    override fun getCompoundDrawableBottom(
        context: Context,
        year: Int,
        month: Int,
        day: Int,
        valid: Boolean,
        selected: Boolean
    ): Drawable? = selectIndicatorDrawable(year, month, day, valid, selected)

    private fun selectIndicatorDrawable(
        year: Int,
        month: Int,
        day: Int,
        valid: Boolean,
        selected: Boolean
    ): Drawable? = when {
        (!valid || !shouldShowIndicator(year, month, day)) -> getTransparentIndicator()
        selected -> getSelectedIndicator()
        else -> getDefaultIndicator()
    }


    private fun shouldShowIndicator(
        year: Int,
        month: Int,
        day: Int
    ): Boolean {
        return true
    }



    private fun Drawable.getInsetDrawable() =
        InsetDrawable(this, 0, 0, 0, 6.dpToPx()).apply { setBounds(0, 0, 5.dpToPx(), (6+5).dpToPx()) }

    private fun getTransparentIndicator() =
        R.drawable.ic_day_indicator_transparent.getDrawable()?.getInsetDrawable()
    private fun getDefaultIndicator() = R.drawable.ic_day_indicator_blue.getDrawable()?.getInsetDrawable()
    private fun getSelectedIndicator() = R.drawable.ic_day_indicator_white.getDrawable()?.getInsetDrawable()

    private fun getTopSpacer() = R.drawable.ic_day_indicator_spacer.getDrawable()

    private fun createSpacerDrawable(size: Int): Drawable {
        val spacer: Drawable = ColorDrawable(Color.TRANSPARENT)
        spacer.setBounds(0, 0, size, size)
        return spacer
    }

    private fun Int.getDrawable() = AppCompatResources.getDrawable(context, this)

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<CircleIndicatorDayDecorator> {
        override fun createFromParcel(parcel: Parcel): CircleIndicatorDayDecorator {
            return CircleIndicatorDayDecorator(parcel)
        }

        override fun newArray(size: Int): Array<CircleIndicatorDayDecorator?> {
            return arrayOfNulls(size)
        }
    }

    private fun getUtcCalendar(): Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))


}