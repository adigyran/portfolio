package com.aya.digital.core.feature.tabviews.doctorsearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class DoctorSearchMode: Parcelable {
    object ShowingAllDoctors:DoctorSearchMode()
    object ShowingFavoriteDoctors:DoctorSearchMode()
}
