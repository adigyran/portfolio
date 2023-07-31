package com.aya.digital.feature.bottomdialogs.codedialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CodeDialogInputState:Parcelable{
    data class ConfirmEmail(val email:String):CodeDialogInputState()
    data class ConfirmPhone(val phone: String):CodeDialogInputState()
}
