package com.aya.digital.core.domain.profile.security.changeemail.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class ChangeEmailUseCaseImpl(private val authRepository: AuthRepository) : ChangeEmailUseCase {
    override fun invoke(code: String, newEmail: String): Single<RequestResultModel<Boolean>> =
        authRepository.changeEmail(code = code, email = newEmail)
            .mapResult({it.asResultModel()},{it.toModelError()})
}