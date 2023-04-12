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

    @GET("appointments?start=2023-04-10T23:00:00.305Z&end=2023-04-16T23:00:00.305Z")
    fun getAppointments(
    ): Flowable<List<AppointmentResponse>>
}