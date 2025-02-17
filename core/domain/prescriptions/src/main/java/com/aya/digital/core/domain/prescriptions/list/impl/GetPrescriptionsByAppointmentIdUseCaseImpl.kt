package com.aya.digital.core.domain.prescriptions.list.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionsByAppointmentIdUseCase
import io.reactivex.rxjava3.core.Single

internal class GetPrescriptionsByAppointmentIdUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val prescriptionsRepository: PrescriptionsRepository
) : GetPrescriptionsByAppointmentIdUseCase {
    override fun invoke(
        appointmentId: Int
    ): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}