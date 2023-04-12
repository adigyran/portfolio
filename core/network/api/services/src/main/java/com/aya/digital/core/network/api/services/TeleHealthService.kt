package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.CreateAppointmentBody
import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.request.RepeatAppointmentBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.*

interface TeleHealthService {

    @POST("token")
    fun getRoomToken(@Body getTelehealthRoomTokenBody: GetTelehealthRoomTokenBody): Single<TelehealthRoomTokenResponse>
}