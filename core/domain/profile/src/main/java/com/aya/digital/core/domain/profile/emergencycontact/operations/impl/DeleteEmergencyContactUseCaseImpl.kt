package com.aya.digital.core.domain.profile.emergencycontact.operations.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.emergencycontact.operations.DeleteEmergencyContactUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class DeleteEmergencyContactUseCaseImpl(private val profileRepository: ProfileRepository,
                                                 private val progressRepository: ProgressRepository
) : DeleteEmergencyContactUseCase {
    override fun invoke(id: Int): Single<RequestResultModel<Boolean>> =
        profileRepository.deleteEmergencyContact(id)
            .trackProgress(progressRepository)
            .mapResult({true.asResultModel()},{it.toModelError()})
}