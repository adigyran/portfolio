package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PrescriptionsService {
    @PUT("settings/prescriptions/subscribe")
    fun subscribeToPrescriptions(@Body prescriptionSubscribeBody: PrescriptionSubscribeBody): Completable

}