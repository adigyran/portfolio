package com.aya.digital.core.domain.prescriptions.list

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface GetPrescriptionByIdUseCase {
    operator fun invoke(appointmentId:Int,doctorId:Int,patientId:Int): Single<RequestResultModel<Boolean>>

}