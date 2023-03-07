package com.aya.digital.core.domain.auth.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.domain.auth.model.VerifyCodeResult
import com.aya.digital.core.domain.auth.VerifyRegistrationUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class VerifyRegistrationUseCaseImpl(private val authRepository: AuthRepository) :
    VerifyRegistrationUseCase {
    override fun invoke(code: String): Single<RequestResultModel<VerifyCodeResult>> = authRepository
        .verifyCode(code)
        .mapResult({(if(it) VerifyCodeResult.Success else VerifyCodeResult.Error).asResultModel()},{it.toModelError()})
}