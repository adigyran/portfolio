package com.aya.digital.core.domain.prescriptions.list.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionsByActorIdUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetDoctorPrescriptionsByPractitionerIdUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val prescriptionsRepository: PrescriptionsRepository
) : GetPrescriptionsByActorIdUseCase {
    override fun invoke(
        actorId: Int
    ): Single<RequestResultModel<Boolean>> =
        prescriptionsRepository.getDoctorPrescriptionsByPractitionerId(actorId)
            .trackProgress(progressRepository)
            .mapResult({ it.asResultModel() }, { it.toModelError() })
}