package com.aya.digital.core.feature.auth.restorepassword.viewmodel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal sealed class RestorePasswordOperationState : Parcelable {
    object RestoringEmailInput : RestorePasswordOperationState()
    object RestoringChangePassword : RestorePasswordOperationState()
    object ChangeTempPassword : RestorePasswordOperationState()
}
