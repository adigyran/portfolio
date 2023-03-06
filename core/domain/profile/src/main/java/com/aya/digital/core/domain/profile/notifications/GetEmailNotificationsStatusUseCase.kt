package com.aya.digital.core.domain.profile.notifications

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceModel
import io.reactivex.rxjava3.core.Single

interface GetEmailNotificationsStatusUseCase {
    operator fun invoke(): Single<RequestResultModel<Boolean>>
}

