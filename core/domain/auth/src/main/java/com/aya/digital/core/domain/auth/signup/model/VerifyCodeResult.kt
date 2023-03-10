package com.aya.digital.core.domain.auth.signup.model

sealed class VerifyCodeResult {
    object Success : VerifyCodeResult()
    object Error : VerifyCodeResult()
}