package com.aya.digital.core.domain.profile.emergencycontact.operations.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.emergencycontact.operations.CreateEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.operations.SaveEmergencyContactUseCase
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.EmergencyContactBody
import io.reactivex.rxjava3.core.Single

internal class SaveEmergencyContactUseCaseImpl(private val profileRepository: ProfileRepository,
                                               private val progressRepository: ProgressRepository,
                                               private val createEmergencyContactUseCase: CreateEmergencyContactUseCase
) : SaveEmergencyContactUseCase {

    override fun invoke(
        id: Int?,
        name: String,
        phone: String,
        summary: String,
        type: Int
    ): Single<RequestResultModel<Boolean>> = if(id==null) createEmergencyContactUseCase(name, phone, summary, type)
    else profileRepository.updateEmergencyContact(id, EmergencyContactBody(name, phone,summary, type)).trackProgress(progressRepository)
        .mapResult({true.asResultModel()},{it.toModelError()})
}