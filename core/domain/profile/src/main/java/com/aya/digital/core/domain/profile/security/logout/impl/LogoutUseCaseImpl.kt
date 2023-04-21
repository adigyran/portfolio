package com.aya.digital.core.domain.profile.security.logout.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.security.logout.LogoutUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class LogoutUseCaseImpl(private val profileRepository: ProfileRepository) : LogoutUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> = profileRepository
        .logout()
        .mapResult({it.asResultModel()},{it.toModelError()})
}