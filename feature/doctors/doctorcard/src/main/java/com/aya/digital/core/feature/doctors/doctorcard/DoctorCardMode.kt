package com.aya.digital.core.feature.doctors.doctorcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class DoctorCardMode: Parcelable {
    object ShowingSlots:DoctorCardMode()
    object ShowingDetailsInfo:DoctorCardMode()
}
