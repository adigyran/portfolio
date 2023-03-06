package com.aya.digital.core.domain.profile.notifications.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.notifications.SetEmailNotificationsStatusUseCase
import io.reactivex.rxjava3.core.Single

internal class SetEmailNotificationsStatusUseCaseImpl : SetEmailNotificationsStatusUseCase {
    override fun invoke(status: Boolean): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}