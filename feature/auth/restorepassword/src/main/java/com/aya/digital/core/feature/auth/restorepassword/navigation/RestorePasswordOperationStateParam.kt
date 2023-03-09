package com.aya.digital.core.feature.auth.restorepassword.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class RestorePasswordOperationStateParam : Parcelable {
    object ChangeTempPass : RestorePasswordOperationStateParam()
    object RestorePassword : RestorePasswordOperationStateParam()
}
