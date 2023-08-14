package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.Body

interface TeleHealthDataSource {
   fun getRoomToken(getTelehealthRoomTokenBody: GetTelehealthRoomTokenBody) : Single<TelehealthRoomTokenResponse>

   fun getTimeWindow(): Single<TelehealthWaitTimeResponse>
}