package com.aya.digital.core.domain.prescriptions.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import io.reactivex.rxjava3.core.Single

interface SubscribeToPrescriptionsUseCase {
    operator fun invoke(appointmentId:Int,doctorId:Int,patientId:Int): Single<RequestResultModel<Boolean>>

}