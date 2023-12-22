package com.aya.digital.core.domain.prescriptions.list.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionsByActorIdUseCase
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionsUseCase
import com.aya.digital.core.ext.flatMapResult
import io.reactivex.rxjava3.core.Single

internal class GetPrescriptionsUseCaseImpl(
    private val getPrescriptionsByActorIdUseCase: GetPrescriptionsByActorIdUseCase,
    private val profileRepository: ProfileRepository
) : GetPrescriptionsUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> =
        profileRepository.currentProfileId()
            .flatMapResult({ profileId ->
                getPrescriptionsByActorIdUseCase(profileId)
            }, { Single.just(it.toModelError()) })

}