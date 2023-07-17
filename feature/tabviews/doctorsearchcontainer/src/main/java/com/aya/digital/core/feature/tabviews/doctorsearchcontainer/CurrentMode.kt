package com.aya.digital.core.feature.tabviews.doctorsearchcontainer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CurrentMode(val page:Int):Parcelable{
    object List:CurrentMode(page = 0)

    object Map:CurrentMode(page = 1)
}
