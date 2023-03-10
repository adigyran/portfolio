package com.aya.digital.core.domain.auth.restorepassword.model

sealed class RestoreCodeResult {
    data class Success(val userKey:String) : RestoreCodeResult()
    object Error : RestoreCodeResult()
}