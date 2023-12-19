package com.aya.digital.core.domain.prescriptions.edit

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface EditPrescriptionByPrescriptionIdUseCase {
    operator fun invoke(prescriptionId: Int, content:String): Single<RequestResultModel<Boolean>>

}