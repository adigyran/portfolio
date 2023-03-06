package com.aya.digital.core.domain.profile.security.summary

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.summary.model.SecuritySummaryModel
import io.reactivex.rxjava3.core.Single

internal class GetSecuritySummaryUseCaseImpl : GetSecuritySummaryUseCase {
    override fun invoke(): Single<RequestResultModel<SecuritySummaryModel>> {
        TODO("Not yet implemented")
    }
}