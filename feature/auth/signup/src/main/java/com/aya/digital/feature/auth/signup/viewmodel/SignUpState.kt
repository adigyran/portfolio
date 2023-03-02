package com.aya.digital.feature.auth.signup.viewmodel

import android.os.Parcelable
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize


/*{
    "login":"artem.rogov@aya-dev.com",
    "firstName":"Artem",
    "lastName":"Rogov",
    "email":"artem.rogov@aya-dev.com",
    "password":"PURpTckqWVVyf4@Q#",
    "confirm_password":"PURpTckqWVVyf4@Q#",
    "insurances":[1014,1013,1012]
}*/
@Parcelize
data class SignUpState(
    val email: String = "",
    val emailError: String? = null,
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val insurances: Set<InsuranceCompany> = setOf<InsuranceCompany>(),
    val code:String? = null
) : BaseState

@Parcelize
data class InsuranceCompany(val id: Int, val name: String) : Parcelable