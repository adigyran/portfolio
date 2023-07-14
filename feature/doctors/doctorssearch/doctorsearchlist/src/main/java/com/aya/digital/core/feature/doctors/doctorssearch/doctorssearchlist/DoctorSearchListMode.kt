package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class DoctorSearchListMode: Parcelable {
    object ShowingAllDoctors: DoctorSearchListMode()
    object ShowingFavoriteDoctors: DoctorSearchListMode()
}
