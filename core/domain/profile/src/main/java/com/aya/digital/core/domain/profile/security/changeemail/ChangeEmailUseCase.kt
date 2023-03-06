package com.aya.digital.core.domain.profile.security.changeemail

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceModel
import com.aya.digital.core.domain.profile.security.summary.model.SecuritySummaryModel
import io.reactivex.rxjava3.core.Single

interface ChangeEmailUseCase {
    operator fun invoke(code:String, newEmail:String): Single<RequestResultModel<Boolean>>
}

