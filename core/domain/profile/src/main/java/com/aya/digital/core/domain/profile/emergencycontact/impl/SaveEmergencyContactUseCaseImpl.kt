package com.aya.digital.core.domain.profile.emergencycontact.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.emergencycontact.SaveEmergencyContactUseCase
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.EmergencyContactBody
import io.reactivex.rxjava3.core.Single

internal class SaveEmergencyContactUseCaseImpl(private val profileRepository: ProfileRepository,
                                               private val progressRepository: ProgressRepository
) : SaveEmergencyContactUseCase {
    override fun invoke(name: String, phone: String): Single<RequestResultModel<Boolean>> =
        profileRepository.updateEmergencyContact(EmergencyContactBody(name, phone))
            .trackProgress(progressRepository)
            .mapResult({true.asResultModel()},{it.toModelError()})
}