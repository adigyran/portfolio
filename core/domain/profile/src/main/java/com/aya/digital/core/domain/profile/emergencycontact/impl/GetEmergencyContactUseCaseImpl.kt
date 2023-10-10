package com.aya.digital.core.domain.profile.emergencycontact.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactModel
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactTypeModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetEmergencyContactUseCaseImpl(private val profileRepository: ProfileRepository,
                                              private val progressRepository: ProgressRepository
) : GetEmergencyContactUseCase {
    override fun invoke(): Single<RequestResultModel<EmergencyContactModel>> =
        profileRepository.getEmergencyContact()
            .trackProgress(progressRepository)
            .mapResult({EmergencyContactModel(it.id,it.name,it.phone,it.summary,
                EmergencyContactTypeModel(it.type?:0,"fff")
            ).asResultModel()},{it.toModelError()})
}