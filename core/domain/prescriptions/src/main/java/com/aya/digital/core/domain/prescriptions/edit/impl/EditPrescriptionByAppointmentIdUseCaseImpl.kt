package com.aya.digital.core.domain.prescriptions.edit.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.prescriptions.edit.EditPrescriptionByAppointmentIdUseCase
import io.reactivex.rxjava3.core.Single

internal class EditPrescriptionByAppointmentIdUseCaseImpl(private val progressRepository: ProgressRepository, private val prescriptionsRepository: PrescriptionsRepository) : EditPrescriptionByAppointmentIdUseCase {
    override fun invoke(appointmentId: Int): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}