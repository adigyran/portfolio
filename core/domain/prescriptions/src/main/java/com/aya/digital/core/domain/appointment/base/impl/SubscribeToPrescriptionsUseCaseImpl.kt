package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.base.SubscribeToPrescriptionsUseCase
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class SubscribeToPrescriptionsUseCaseImpl(private val progressRepository: ProgressRepository,private val prescriptionsRepository: PrescriptionsRepository) : SubscribeToPrescriptionsUseCase {
    override fun invoke(
        appointmentId: Int,
        doctorId: Int,
        patientId: Int
    ): Single<RequestResultModel<Boolean>> = prescriptionsRepository
        .subscribeToPrescriptions(doctorId,patientId, appointmentId)
        .trackProgress(progressRepository)
        .mapResult({it.asResultModel()},{it.toModelError()})
}