package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import java.time.LocalDate

sealed class ProfileGeneralInfoEditSideEffect : BaseSideEffect {
    data class ShowBirthdayDatePicker(val selectedDate:LocalDate?) : ProfileGeneralInfoEditSideEffect()
}