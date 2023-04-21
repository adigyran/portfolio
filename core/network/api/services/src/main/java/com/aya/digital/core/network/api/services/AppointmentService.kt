package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.CreateAppointmentBody
import com.aya.digital.core.network.model.request.RepeatAppointmentBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.*

interface AppointmentService {
    @GET("appointments")
    fun getAppointments(@Query("start") start: String,
                            @Query("end") end: String): Flowable<List<AppointmentResponse>>

    @POST("appointments")
    fun createAppointment(@Body body:CreateAppointmentBody):Single<AppointmentResponse>

}