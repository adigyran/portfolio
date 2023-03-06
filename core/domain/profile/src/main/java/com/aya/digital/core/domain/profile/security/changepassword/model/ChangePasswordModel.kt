package com.aya.digital.core.domain.profile.security.changepassword.model

data class ChangePasswordModel(val currentPassword:String, val newPassword:String, val newPasswordRepeat:String)
