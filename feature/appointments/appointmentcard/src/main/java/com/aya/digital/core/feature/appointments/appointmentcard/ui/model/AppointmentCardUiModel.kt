package com.aya.digital.core.feature.appointments.appointmentcard.ui.model

import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.adapters.base.DiffItem

internal data class AppointmentCardUiModel(
    val data: List<DiffItem>? = null,
    val appointmentDateTimeText: String? = null,
    val participantAvatarLink: String? = null,
    val participantLines: Pair<String,String>? = null,
) : BaseUiModel
