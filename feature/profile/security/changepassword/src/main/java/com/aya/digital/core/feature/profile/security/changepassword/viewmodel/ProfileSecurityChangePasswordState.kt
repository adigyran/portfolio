package com.aya.digital.core.feature.profile.security.changepassword.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileSecurityChangePasswordState(val email: String = "",
                                              val emailError: String? = null,
                                              val password: String = "",
                                              val passwordError: String? = null
                       ) : BaseState
