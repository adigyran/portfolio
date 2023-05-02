package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface TeleHealthService {

    @POST("token")
    fun getRoomToken(@Body getTelehealthRoomTokenBody: GetTelehealthRoomTokenBody): Single<TelehealthRoomTokenResponse>
}