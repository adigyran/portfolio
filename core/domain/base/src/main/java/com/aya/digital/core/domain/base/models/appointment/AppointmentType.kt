package com.aya.digital.core.domain.base.models.appointment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class AppointmentType : Parcelable {
    object Offline : AppointmentType()
    data class Online(val preMins:Long?=null) : AppointmentType()
}
