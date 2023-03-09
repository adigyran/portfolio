package com.aya.digital.core.domain.profile.security.changeemail.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class ChangeEmailGetCodeUseCaseImpl(private val authRepository: AuthRepository) : ChangeEmailGetCodeUseCase {
    override fun invoke(email:String): Single<RequestResultModel<Boolean>> =
        authRepository.changeEmailSendCode(email)
            .mapResult({it.asResultModel()},{it.toModelError()})
}