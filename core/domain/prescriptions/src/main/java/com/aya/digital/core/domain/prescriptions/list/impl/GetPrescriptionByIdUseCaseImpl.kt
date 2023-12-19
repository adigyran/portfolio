package com.aya.digital.core.domain.prescriptions.list.impl

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.prescriptions.list.GetPrescriptionByIdUseCase
import io.reactivex.rxjava3.core.Single

internal class GetPrescriptionByIdUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val prescriptionsRepository: PrescriptionsRepository
) : GetPrescriptionByIdUseCase {
    override fun invoke(
        prescriptionId: Int
    ): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}