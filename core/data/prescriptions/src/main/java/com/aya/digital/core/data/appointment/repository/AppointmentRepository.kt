package com.aya.digital.core.data.appointment.repository

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.TelehealthWaitTimeModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

interface AppointmentRepository {
    fun getRoomTokenById(roomId: Int): Single<RequestResult<String>>

    fun getTimeWindow():Single<RequestResult<TelehealthWaitTimeModel>>

    fun getAppointments(
        start: LocalDateTime,
        end: LocalDateTime
    ): Flowable<RequestResult<List<Appointment>>>

    fun getAppointmentById(
        appointmentId:Int
    ): Single<RequestResult<Appointment>>

    fun cancelAppointment(appointmentId: Int):Single<RequestResult<Boolean>>
    fun createAppointment(slotId: Int, comment: String): Single<RequestResult<Appointment>>
}