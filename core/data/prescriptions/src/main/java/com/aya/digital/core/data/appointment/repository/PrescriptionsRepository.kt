package com.aya.digital.core.data.appointment.repository


import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

interface PrescriptionsRepository {
  fun subscribeToPrescriptions(practitionerId: Int, patientId: Int, appointmentId: Int):Single<RequestResult<Boolean>>
}