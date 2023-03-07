package com.aya.digital.core.feature.auth.signin.viewmodel.mode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class RestorePasswordOperationState : Parcelable {
    object RestoringEmailInput : RestorePasswordOperationState()
    object RestoringChangePassword : RestorePasswordOperationState()
    object ChangeTempPassword : RestorePasswordOperationState()
}
