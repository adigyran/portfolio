package com.aya.digital.core.domain.profile.notifications

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface SetEmailNotificationsStatusUseCase {
    operator fun invoke(status:Boolean): Single<RequestResultModel<Boolean>>
}

