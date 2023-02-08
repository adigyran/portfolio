package com.aya.digital.core.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.Body

interface AppointmentDataSource {
    fun getAppointment(
        id: Int,
    ): Single<com.aya.digital.core.network.model.response.AppointmentResponse>

    fun getAppointments(
        patient: Int?,
        practitioner: Int?,
        slot: Int?,
        page: Int,
        limit: Int,
        period: String?,
        start: LocalDate?,
        end: LocalDate?,
    ): Observable<com.aya.digital.core.network.model.response.base.PagedResponse<com.aya.digital.core.network.model.response.AppointmentResponse>>

    fun createAppointment(@Body body: com.aya.digital.core.network.model.request.CreateAppointmentBody): Single<com.aya.digital.core.network.model.response.AppointmentResponse>

    fun rescheduleAppointment(
        appointmentId: Int,
        slotId: Int
    ): Completable

    fun repeatAppointment(@Body body: com.aya.digital.core.network.model.request.RepeatAppointmentBody): Single<com.aya.digital.core.network.model.response.AppointmentResponse>

    fun cancelAppointment(
        appointmentId: Int,
    ): Completable
}