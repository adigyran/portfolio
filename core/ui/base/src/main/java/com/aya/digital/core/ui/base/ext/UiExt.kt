package com.aya.digital.core.ui.base.ext

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.text.style.TextAppearanceSpan
import android.util.Log
import com.aya.digital.core.ui.base.utils.TouchableSpan
import timber.log.Timber

fun String.createSpannableText(
    mNormalTextColor: Int,
    mPressedTextColor: Int,
    flags: Int,
    context: Context,
    mainTextAppearance: Int,
    spannableObjectsTextAppearance: Int,
    spannableObjects: List<SpannableObject>
): Spannable {
    val spans = spannableObjects.map {
        SpannableStringBuilder()
            .createTouchableSpan(
                mNormalTextColor,
                mPressedTextColor,
                context,
                spannableObjectsTextAppearance,
                it.onClick,
                it.text,
                flags
            )
    }.toTypedArray()
    return this.formatSpannable(context, mainTextAppearance, *spans)
}

data class SpannableObject(val text: CharSequence, val onClick: () -> Unit)

fun SpannableStringBuilder.createTouchableSpan(
    mNormalTextColor: Int,
    mPressedTextColor: Int,
    context: Context,
    spannableObjectsTextAppearance: Int,
    onClick: () -> Unit,
    text: CharSequence,
    flags: Int,
): SpannableStringBuilder {
    appendExtended(
        text, listOf(
            TextAppearanceSpan(context, spannableObjectsTextAppearance),
            TouchableSpan(
                mNormalTextColor,
                mPressedTextColor
            )
            {
                onClick()
            },
        ), flags
    )
    return this
}

fun SpannableStringBuilder.appendExtended(
    text: CharSequence,
    what: Any,
    flags: Int,
): SpannableStringBuilder {
    val start = length
    append(text)
    setSpan(what, start, length, flags)
    return this
}

fun SpannableStringBuilder.appendExtended(
    text: CharSequence,
    what: List<Any>,
    flags: Int,
): SpannableStringBuilder {
    val start = length
    append(text)
    what.forEach { setSpan(it, start, length, flags) }
    return this
}

fun String.formatSpannable(
    context: Context,
    mainTextAppearance: Int,
    vararg spans: CharSequence
): Spannable {
    val result = SpannableStringBuilder()
    when {
        spans.size != this.split("%s").size - 1 ->
            Timber.tag("formatSpannable")
                .e("cannot format '" + this + "' with " + spans.size + " arguments")
        !this.contains("%s") -> result.appendExtendedTextAppearance(
            this,
            context,
            mainTextAppearance
        )
        else -> {
            var str = this
            var spanIndex = 0
            while (str.contains("%s")) {
                val preStr = str.substring(0, str.indexOf("%s"))
                result.appendExtendedTextAppearance(preStr, context, mainTextAppearance)
                result.append(spans[spanIndex] ?: "")
                str = str.substring(str.indexOf("%s") + 2)
                spanIndex++
            }
            if (str.isNotEmpty()) {
                result.appendExtendedTextAppearance(str, context, mainTextAppearance)
            }
        }
    }
    return result
}

private fun SpannableStringBuilder.appendExtendedTextAppearance(
    text: CharSequence,
    context: Context,
    textAppearance: Int
) =
    this.appendExtended(
        text,
        listOf(TextAppearanceSpan(context, textAppearance)),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

fun SpannableStringBuilder.appendExtendedImage(
    image: ImageSpan,
    flags: Int,
): SpannableStringBuilder {
    append(" ")

    setSpan(image, length - 1, length, flags)
    return this
}