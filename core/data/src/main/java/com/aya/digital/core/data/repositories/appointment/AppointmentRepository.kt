package com.aya.digital.core.data.repositories.appointment

import com.aya.digital.core.data.model.PaginationPageModel
import com.aya.digital.core.data.model.appointment.Appointment
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDateTime

interface AppointmentRepository {

    fun createAppointment(
        slotId: Int,
        patientId: Int,
        comment: String?,
        description: String?,
    ): Single<RequestResult<Appointment>>

    fun rescheduleAppointment(
        previousAppointmentId: Int,
        newSlotId: Int
    ): Single<RequestResult<Unit>>

    fun cancelAppointment(
        appointmentId: Int,
    ): Single<RequestResult<Unit>>

    fun repeatAppointment(
        appointmentId: Int,
        slotId: Int,
    ): Single<RequestResult<Appointment>>

    fun getAppointments(
        page: Int,
        patient: Int?,
        practitioner: Int?,
        slot: Int? = null,
        start: LocalDateTime? = null,
        limit: Int = 10,
        period: String? = null,
        end: LocalDateTime? = null,
    ): Flowable<RequestResult<PaginationPageModel<Appointment>>>

    suspend fun getAppointment(
        id: Int,
    ): Single<RequestResult<Appointment>>
}