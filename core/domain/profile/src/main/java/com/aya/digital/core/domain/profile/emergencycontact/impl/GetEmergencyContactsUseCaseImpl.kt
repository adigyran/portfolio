package com.aya.digital.core.domain.profile.emergencycontact.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactsUseCase
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactModel
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactTypeModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetEmergencyContactsUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : GetEmergencyContactsUseCase {
    override fun invoke(): Single<RequestResultModel<List<EmergencyContactModel>>> =
        profileRepository.getEmergencyContacts()
            .trackProgress(progressRepository)
            .mapResult({ contacts ->
                contacts.map { EmergencyContactModel(it.id,it.name,it.phone,it.summary,
                    EmergencyContactTypeModel(it.type?:0,"fff")
                ) }.asResultModel()
            }, { it.toModelError() })
}