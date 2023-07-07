package com.aya.digital.core.domain.profile.notifications.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.notifications.SetNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.model.NotificationsStatusModel
import com.aya.digital.core.domain.profile.notifications.model.toModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class SetNotificationsStatusUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : SetNotificationsStatusUseCase {

    override fun invoke(status: NotificationsStatusModel): Single<RequestResultModel<NotificationsStatusModel>> =
        profileRepository.updateNotificationStatus(NotificationsStatus(status.emailNotifications,status.smsNotifications))
            .trackProgress(progressRepository)
            .mapResult({it.toModel().asResultModel()},{it.toModelError()})
}