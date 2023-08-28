package com.aya.digital.core.domain.schedule.base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class SlotModelType:Parcelable{
    object Online:SlotModelType()
    object OffLine:SlotModelType()
    object Unknown:SlotModelType()
}

fun String?.getSlotType() = when
{
    !isNullOrBlank() && contains("online",true) -> SlotModelType.Online
    else -> SlotModelType.Unknown
}
