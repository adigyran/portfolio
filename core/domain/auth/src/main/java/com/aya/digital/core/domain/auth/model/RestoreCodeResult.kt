package com.aya.digital.core.domain.auth.model

sealed class RestoreCodeResult {
    data class Success(val userKey:String) : RestoreCodeResult()
    object Error : RestoreCodeResult()
}