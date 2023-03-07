package com.aya.digital.core.domain.auth.model

sealed class VerifyCodeResult {
    object Success : VerifyCodeResult()
    object Error : VerifyCodeResult()
}