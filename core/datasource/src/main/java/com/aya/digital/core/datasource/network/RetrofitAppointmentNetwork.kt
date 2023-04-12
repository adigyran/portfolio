package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.AppointmentDataSource
import com.aya.digital.core.network.api.services.AppointmentService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.response.AppointmentResponse
import io.reactivex.rxjava3.core.Flowable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun appointmentNetworkModule() = DI.Module("appointmentNetworkModule") {
    bind<com.aya.digital.core.datasource.AppointmentDataSource>() with singleton {
        val apiService =
            createApiService<AppointmentService>(instance())
        return@singleton RetrofitTeleHealthNetwork(apiService)
    }
}

class RetrofitAppointmentNetwork(private val networkApi: AppointmentService) :
    AppointmentDataSource {
    override fun getAppointments(): Flowable<List<AppointmentResponse>> = networkApi.getAppointments()

}
