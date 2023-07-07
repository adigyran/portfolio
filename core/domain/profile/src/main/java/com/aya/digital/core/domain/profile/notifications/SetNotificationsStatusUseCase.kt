package com.aya.digital.core.domain.profile.notifications

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.notifications.model.NotificationsStatusModel
import io.reactivex.rxjava3.core.Single

interface SetNotificationsStatusUseCase {
    operator fun invoke(status:NotificationsStatusModel): Single<RequestResultModel<NotificationsStatusModel>>
}

