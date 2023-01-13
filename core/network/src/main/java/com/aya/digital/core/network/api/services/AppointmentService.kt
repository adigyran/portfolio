package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.CreateAppointmentBody
import com.aya.digital.core.network.model.request.RepeatAppointmentBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.*

interface AppointmentService {

    @GET("appointments/{id}")
    fun getAppointment(
        @Path("id") id: Int,
    ): Single<AppointmentResponse>

    @GET("appointments/")
    fun getAppointments(
        @Query("patient") patient: Int?,
        @Query("practitioner") practitioner: Int?,
        @Query("slot") slot: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("period") period: String?,
        @Query("start") start: LocalDate?,
        @Query("end") end: LocalDate?,
    ): Observable<PagedResponse<AppointmentResponse>>

    @POST("appointments/")
    fun createAppointment(@Body body: CreateAppointmentBody): Single<AppointmentResponse>

    @PUT("appointments/reschedule/{appointmentId}/{slotId}")
    fun rescheduleAppointment(
        @Path("appointmentId") appointmentId: Int,
        @Path("slotId") slotId: Int
    ): Completable

    @POST("appointments/")
    fun repeatAppointment(@Body body: RepeatAppointmentBody): Single<AppointmentResponse>

    @PUT("appointments/cancel/{appointmentId}")
    fun cancelAppointment(
        @Path("appointmentId") appointmentId: Int,
    ): Completable
}