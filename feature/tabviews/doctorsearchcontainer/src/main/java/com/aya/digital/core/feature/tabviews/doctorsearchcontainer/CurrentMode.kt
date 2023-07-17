package com.aya.digital.core.feature.tabviews.doctorsearchcontainer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CurrentMode:Parcelable{
    object Map:CurrentMode()
    object List:CurrentMode()
}
