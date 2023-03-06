package com.aya.digital.core.domain.profile.notifications.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.notifications.GetEmailNotificationsStatusUseCase
import io.reactivex.rxjava3.core.Single

internal class GetEmailNotificationsStatusUseCaseImpl : GetEmailNotificationsStatusUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}