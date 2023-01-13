package com.aya.digital.core.datasources

import com.aya.digital.core.network.model.request.CreateAppointmentBody
import com.aya.digital.core.network.model.request.RepeatAppointmentBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.Body

interface AppointmentDataSource {
    fun getAppointment(
        id: Int,
    ): Single<AppointmentResponse>

    fun getAppointments(
        patient: Int?,
        practitioner: Int?,
        slot: Int?,
        page: Int,
        limit: Int,
        period: String?,
        start: LocalDate?,
        end: LocalDate?,
    ): Observable<PagedResponse<AppointmentResponse>>

    fun createAppointment(@Body body: CreateAppointmentBody): Single<AppointmentResponse>

    fun rescheduleAppointment(
        appointmentId: Int,
        slotId: Int
    ): Completable

    fun repeatAppointment(@Body body: RepeatAppointmentBody): Single<AppointmentResponse>

    fun cancelAppointment(
        appointmentId: Int,
    ): Completable
}