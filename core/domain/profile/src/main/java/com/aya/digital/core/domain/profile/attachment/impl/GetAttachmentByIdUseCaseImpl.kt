package com.aya.digital.core.domain.profile.attachment.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.attachment.GetAttachmentByIdUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class GetAttachmentByIdUseCaseImpl(private val profileRepository: ProfileRepository,
                                   private val progressRepository: ProgressRepository
) : GetAttachmentByIdUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>> =
        profileRepository.getAttachmentById(id)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}