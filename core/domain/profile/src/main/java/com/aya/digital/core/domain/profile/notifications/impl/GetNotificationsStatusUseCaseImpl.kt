package com.aya.digital.core.domain.profile.notifications.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.notifications.GetNotificationsStatusUseCase
import com.aya.digital.core.domain.profile.notifications.model.NotificationsStatusModel
import com.aya.digital.core.domain.profile.notifications.model.toModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetNotificationsStatusUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository) : GetNotificationsStatusUseCase {
    override fun invoke(): Single<RequestResultModel<NotificationsStatusModel>> =
        profileRepository.getNotificationsStatus()
            .trackProgress(progressRepository)
            .mapResult({it.toModel().asResultModel()},{it.toModelError()})



}