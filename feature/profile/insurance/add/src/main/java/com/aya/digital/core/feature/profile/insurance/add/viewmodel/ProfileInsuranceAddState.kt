package com.aya.digital.core.feature.profile.insurance.add.viewmodel

import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileInsuranceAddState(val email: String = "",
                                    val emailError: String? = null,
                                    val password: String = "",
                                    val passwordError: String? = null
                       ) : BaseState
