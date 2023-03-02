package com.aya.digital.core.ui.base.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.AnyRes
import androidx.core.content.res.ResourcesCompat.*
import com.aya.digital.core.ext.ContextAware

val Context.animations
    get() = ResourceMapper { resources.getAnimation(it) }
val Context.booleans
    get() = ResourceMapper { resources.getBoolean(it) }
val Context.colors
    get() = ResourceMapper { getColor(resources, it, theme) }
val Context.colorStateLists
    get() = ResourceMapper { getColorStateList(resources, it, theme)!! }
val Context.dimens
    get() = ResourceMapper { resources.getDimension(it) }
val Context.dimensInt
    get() = ResourceMapper { resources.getDimensionPixelSize(it) }
val Context.dimensOffset
    get() = ResourceMapper { resources.getDimensionPixelOffset(it) }
val Context.drawables
    get() = ResourceMapper { getDrawable(resources, it, theme)!! }
val Context.scaledDrawable
    get() = ResourceMapper {
        ScaledDrawable(
            resources, it, theme
        )
    }
val Context.fonts
    get() = ResourceMapper { getFont(this, it)!! }
val Context.intArrays
    get() = ResourceMapper { resources.getIntArray(it) }
val Context.ints
    get() = ResourceMapper { resources.getInteger(it) }
val Context.layouts
    get() = ResourceMapper { resources.getLayout(it) }
val Context.movies
    get() = ResourceMapper { resources.getMovie(it) }
val Context.formattedStrings
    get() = ResourceMapper { FormattedString(resources, it) }
val Context.resourceInfos
    get() = ResourceMapper { ResourceInfo(resources, it) }
val Context.strings
    get() = ResourceMapper { getString(it) }
val Context.stringArrays
    get() = ResourceMapper { resources.getStringArray(it) }
val Context.texts
    get() = ResourceMapper { getText(it) }
val Context.textArrays
    get() = ResourceMapper { resources.getTextArray(it) }
val Context.xmls
    get() = ResourceMapper { resources.getXml(it) }
val Context.typedArrays
    get() = ResourceMapper { resources.obtainTypedArray(it) }
val Context.rawResources
    get() = ResourceMapper { resources.openRawResource(it) }



val ContextAware.animations get() = getContextAware().animations
val ContextAware.booleans get() = getContextAware().booleans
val ContextAware.colors get() = getContextAware().colors
val ContextAware.colorStateLists get() = getContextAware().colorStateLists
val ContextAware.dimens get() = getContextAware().dimens
val ContextAware.dimensInt get() = getContextAware().dimensInt
val ContextAware.dimensOffset get() = getContextAware().dimensOffset
val ContextAware.drawables get() = getContextAware().drawables
val ContextAware.scaledDrawable get() = getContextAware().scaledDrawable
val ContextAware.fonts get() = getContextAware().fonts
val ContextAware.intArrays get() = getContextAware().intArrays
val ContextAware.ints get() = getContextAware().ints
val ContextAware.layouts get() = getContextAware().layouts
val ContextAware.movies get() = getContextAware().movies
val ContextAware.formattedStrings get() = getContextAware().formattedStrings
val ContextAware.resourceInfos get() = getContextAware().resourceInfos
val ContextAware.strings get() = getContextAware().strings
val ContextAware.stringArrays get() = getContextAware().stringArrays
val ContextAware.texts get() = getContextAware().texts
val ContextAware.textArrays get() = getContextAware().textArrays
val ContextAware.xmls get() = getContextAware().xmls
val ContextAware.typedArrays get() = getContextAware().typedArrays
val ContextAware.rawResources get() = getContextAware().rawResources

class FormattedString(
    private val resources: Resources,
    private val resId: Int,
) {
    operator fun invoke(vararg values: Any): String =
        resources.getString(resId, *values)

    operator fun invoke(quantity: Int): String =
        resources.getQuantityString(resId, quantity)

    operator fun invoke(quantity: Int, vararg values: Any): String =
        resources.getQuantityString(resId, quantity, *values)
}

data class ResourceInfo(
    private val resources: Resources,
    private val resId: Int,
    val entryName: String = resources.getResourceEntryName(resId),
    val name: String = resources.getResourceName(resId),
    val packageName: String = resources.getResourcePackageName(resId),
    val typeName: String = resources.getResourceTypeName(resId),
)

class ScaledDrawable(
    private val resources: Resources,
    private val resId: Int,
    private val theme: Resources.Theme,
) {
    operator fun invoke(density: Int): Drawable =
        getDrawableForDensity(resources, resId, density, theme)!!
}

class ResourceMapper<out T>(private val mapRes: (resId: Int) -> T) {
    operator fun get(@AnyRes resId: Int) = mapRes(resId)
}