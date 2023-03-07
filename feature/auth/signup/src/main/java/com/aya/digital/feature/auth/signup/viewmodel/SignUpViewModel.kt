package com.aya.digital.feature.auth.signup.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.data.base.result.models.dictionaries.MultiSelectResultModel
import com.aya.digital.core.domain.auth.SignUpGetSelectedInsurancesUseCase
import com.aya.digital.core.domain.auth.SignUpUseCase
import com.aya.digital.core.domain.auth.model.VerifyCodeResult
import com.aya.digital.core.domain.auth.VerifyRegistrationUseCase
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import com.aya.digital.feature.auth.signup.FieldsTags
import com.aya.digital.feature.auth.signup.navigation.SignUpNavigationEvents
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class SignUpViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val signUpUseCase: SignUpUseCase,
    private val selectedInsurancesUseCase: SignUpGetSelectedInsurancesUseCase,
    private val verifyRegistrationUseCase: VerifyRegistrationUseCase
) :
    BaseViewModel<SignUpState, SignUpSideEffects>() {
    override val container = container<SignUpState, SignUpSideEffects>(
        initialState = SignUpState(),
    )
    {

    }

    fun emailChanged(email: String) = intent {
        if (state.email != email) reduce { state.copy(email = email) }
    }

    fun passwordChanged(tag: Int, password: String) = intent {
        when (tag) {
            FieldsTags.PASSWORD_FIELD_TAG -> {
                if (state.password != password) reduce { state.copy(password = password) }
            }
            FieldsTags.PASSWORD_CONFIRM_FIELD_TAG -> {
                if (state.confirmPassword != password) reduce { state.copy(confirmPassword = password) }
            }
        }
    }

    fun insuranceCompanyFieldSelected(tag: Int) = intent {
        if (tag == FieldsTags.INSURANCE_COMPANY_SELECT_FIELD_TAG) {
            requestInsurance()
        }
    }


    fun nameChanged(tag: Int, name: String) = intent {
        when (tag) {
            FieldsTags.FIRST_NAME_FIELD_TAG -> {
                if (state.firstName != name) reduce { state.copy(firstName = name) }
            }
            FieldsTags.LAST_NAME_FIELD_TAG -> {
                if (state.lastName != name) reduce { state.copy(lastName = name) }
            }
        }
    }

    private fun onInsurancesSelected(items: Set<Int>) = intent {
        if (items.isEmpty()) {
            reduce {
                state.copy(
                    insurances = emptySet()
                )
            }
        } else {
            selectedInsurancesUseCase(items.toList()).asFlow().collect {
                it.processResult({ set ->
                    reduce {
                        state.copy(
                            insurances = set.map { InsuranceCompany(it.id, it.name) }.toSet()
                        )
                    }
                }, {
                    Timber.d("onInsurancesSelected Errir: " + it.toString())
                })
            }
        }
    }

    fun onSignInClicked() = intent {
        coordinatorRouter.sendEvent(SignUpNavigationEvents.SignIn)
    }


    private fun codeEntered(code: String) = intent {
        reduce {
            state.copy(code = code)
        }
        if(code.length == 6) verifyCode()

    }

    private fun verifyCode() = intent {
        val verifyCodeResult = verifyRegistrationUseCase(state.code ?: "").await()
        verifyCodeResult.processResult({
            when (it) {
                VerifyCodeResult.Error -> {reenterCode()}
                VerifyCodeResult.Success -> {
                    coordinatorRouter.sendEvent(SignUpNavigationEvents.SignIn)
                }
            }
        }, {

            Timber.d(it.toString()) })
    }

    private fun reenterCode() {
        requestCode()
    }


    fun onSignUpClicked() = intent {
        val signUpUseCase = signUpUseCase(
            state.email,
            state.firstName,
            state.lastName,
            state.insurances.map { it.id },
            state.password,
            state.confirmPassword
        ).await()
        signUpUseCase.processResult({
            requestCode()
        }, { Timber.d(it.toString()) })
    }

    private fun requestCode() = intent {
        listenForCodeEvent()
        coordinatorRouter.sendEvent(
            SignUpNavigationEvents.EnterCode(
                RequestCodes.CODE_INPUT_REQUEST_CODE,
                state.email
            )
        )
    }

    private fun requestInsurance() = intent {
        listenForInsuranceEvent()
        coordinatorRouter.sendEvent(
            SignUpNavigationEvents.SelectInsuranceCompanies(
                RequestCodes.INSURANCE_LIST_REQUEST_CODE,
                state.insurances.map { it.id }.toSet()
            )
        )
    }

    private fun listenForInsuranceEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.INSURANCE_LIST_REQUEST_CODE) {
            if (it is MultiSelectResultModel) onInsurancesSelected(it.selectedItems)
        }
    }

    private fun listenForCodeEvent() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CODE_INPUT_REQUEST_CODE) {
            if (it is CodeResultModel) {
                codeEntered(it.code)
            }
        }
    }

}

