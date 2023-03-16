package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import java.time.LocalDate

sealed class ProfileGeneralInfoEditSideEffect : BaseSideEffect {
    data class Error(val error: BaseViewModel.ErrorSideEffect) : ProfileGeneralInfoEditSideEffect()
    data class ShowBirthdayDatePicker(val selectedDate: LocalDate?) :
        ProfileGeneralInfoEditSideEffect()
    object SelectAvatar:ProfileGeneralInfoEditSideEffect()
}