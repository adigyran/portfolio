package com.aya.digital.core.domain.prescriptions.edit.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.prescriptions.edit.EditPrescriptionByPrescriptionIdUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class EditPrescriptionByPrescriptionIdUseCaseImpl(private val progressRepository: ProgressRepository, private val prescriptionsRepository: PrescriptionsRepository) : EditPrescriptionByPrescriptionIdUseCase {
    override fun invoke(prescriptionId: Int, content: String): Single<RequestResultModel<Boolean>>  =
        prescriptionsRepository.editPrescriptionByPrescriptionId(prescriptionId,content)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}