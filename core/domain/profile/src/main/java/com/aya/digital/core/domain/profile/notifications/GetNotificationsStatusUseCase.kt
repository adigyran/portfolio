package com.aya.digital.core.domain.profile.notifications

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.notifications.model.NotificationsStatusModel
import io.reactivex.rxjava3.core.Single

interface GetNotificationsStatusUseCase {
    operator fun invoke(): Single<RequestResultModel<NotificationsStatusModel>>
}

