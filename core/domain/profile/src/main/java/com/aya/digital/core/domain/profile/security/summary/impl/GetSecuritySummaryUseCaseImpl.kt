package com.aya.digital.core.domain.profile.security.summary.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.security.summary.GetSecuritySummaryUseCase
import com.aya.digital.core.domain.profile.security.summary.model.SecuritySummaryModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetSecuritySummaryUseCaseImpl(private val profileRepository: ProfileRepository) :
    GetSecuritySummaryUseCase {
    override fun invoke(): Single<RequestResultModel<SecuritySummaryModel>> = profileRepository
        .currentProfile()
        .mapResult({ SecuritySummaryModel(it.email ?: "", "*******").asResultModel() },
            { it.toModelError() })
}