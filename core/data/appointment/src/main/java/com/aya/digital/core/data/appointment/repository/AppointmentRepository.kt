package com.aya.digital.core.data.appointment.repository

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDateTime

interface AppointmentRepository {
    fun getRoomTokenById(roomId: Int): Single<RequestResult<String>>

    fun getAppointments(
        start: LocalDateTime,
        end: LocalDateTime
    ): Flowable<RequestResult<List<Appointment>>>

    fun createAppointment(slotId: Int, comment: String): Single<RequestResult<Appointment>>
}