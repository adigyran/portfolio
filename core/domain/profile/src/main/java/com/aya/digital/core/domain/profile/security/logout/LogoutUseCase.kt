package com.aya.digital.core.domain.profile.security.logout

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.summary.model.SecuritySummaryModel
import io.reactivex.rxjava3.core.Single

interface LogoutUseCase {
    operator fun invoke(): Single<RequestResultModel<Boolean>>
}

