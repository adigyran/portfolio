package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.CreateAppointmentBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface AppointmentService {
    @GET("api/appointments")
    fun getAppointments(@Query("start") start: String,
                            @Query("end") end: String): Flowable<List<AppointmentResponse>>

    @GET("api/appointments/{id}")
    fun getAppointmentById(@Path("id") appointmentId:Int): Single<AppointmentResponse>

    @POST("api/appointments")
    fun createAppointment(@Body body:CreateAppointmentBody):Single<AppointmentResponse>

    @PUT("api/appointments/cancel/{id}")
    fun cancelAppointment(@Path("id") appointmentId:Int): Completable

}