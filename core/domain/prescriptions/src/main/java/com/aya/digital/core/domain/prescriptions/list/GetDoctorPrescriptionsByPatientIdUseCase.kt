package com.aya.digital.core.domain.prescriptions.list

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface GetDoctorPrescriptionsByPatientIdUseCase {
    operator fun invoke(patientId:Int): Single<RequestResultModel<Boolean>>

}