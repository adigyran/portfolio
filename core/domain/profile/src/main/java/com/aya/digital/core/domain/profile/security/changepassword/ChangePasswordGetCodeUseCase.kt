package com.aya.digital.core.domain.profile.security.changepassword

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceModel
import com.aya.digital.core.domain.profile.security.summary.model.SecuritySummaryModel
import io.reactivex.rxjava3.core.Single

interface ChangePasswordGetCodeUseCase {
    operator fun invoke(): Single<RequestResultModel<Boolean>>
}

