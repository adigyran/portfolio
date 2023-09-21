package com.aya.digital.core.feature.profile.generalinfo.edit.navigation

import com.aya.digital.core.data.base.result.models.auth.PasswordRestoreResultModel
import com.aya.digital.core.data.base.result.models.profile.ProfileSaveResult
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileGeneralInfoEditNavigationEvents : CoordinatorEvent() {

    sealed class FieldSelection(val requestCode: String) :
        ProfileGeneralInfoEditNavigationEvents() {
        class SelectLanguages(requestCode: String, val selectedLanguages: List<Int>) :
            FieldSelection(requestCode)

        class SelectSpecialities(requestCode: String, val selectSpecialities: List<Int>) :
            FieldSelection(requestCode)
        class SelectDegrees(requestCode: String, val selectDegrees: List<Int>) :
            FieldSelection(requestCode)
        object StubField : FieldSelection("")
    }

    data class FinishWithResult(val requestCode: String, val result: ProfileSaveResult) :
        ProfileGeneralInfoEditNavigationEvents()

    object ErrorEvent:ProfileGeneralInfoEditNavigationEvents()
}