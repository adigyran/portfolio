package com.aya.digital.core.domain.auth.restorepassword.model

data class RestorePasswordChangePasswordModel(val token:String, val newPassword: String,val newPasswordDuplicate: String)