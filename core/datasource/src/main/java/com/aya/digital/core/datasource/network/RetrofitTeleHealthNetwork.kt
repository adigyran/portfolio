package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.AppointmentDataSource
import com.aya.digital.core.datasource.TeleHealthDataSource
import com.aya.digital.core.network.api.services.AppointmentService
import com.aya.digital.core.network.api.services.TeleHealthService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun telehealthNetworkModule() = DI.Module("telehealthNetworkModule") {
    bind<TeleHealthDataSource>() with singleton {
        val apiService =
            createApiService<TeleHealthService>(instance(RetrofitTags.RETROFIT_TELEHEALTH_TOKEN_TAG))
        return@singleton RetrofitTeleHealthNetwork(apiService)
    }
}

class RetrofitTeleHealthNetwork(private val networkApi: TeleHealthService) :
    TeleHealthDataSource {
    override fun getRoomToken(getTelehealthRoomTokenBody: GetTelehealthRoomTokenBody): Single<TelehealthRoomTokenResponse> = networkApi.getRoomToken(getTelehealthRoomTokenBody)
    override fun getTimeWindow(): Single<TelehealthWaitTimeResponse> = networkApi.getTimeWindow()

}
