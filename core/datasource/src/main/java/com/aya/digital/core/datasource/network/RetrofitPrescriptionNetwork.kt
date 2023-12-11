package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.PrescriptionsDataSource
import com.aya.digital.core.datasource.TeleHealthDataSource
import com.aya.digital.core.network.api.services.PrescriptionsService
import com.aya.digital.core.network.api.services.TeleHealthService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun prescriptionNetworkModule() = DI.Module("prescriptionNetworkModule") {
    bind<PrescriptionsDataSource>() with singleton {
        val apiService =
            createApiService<PrescriptionsService>(instance())
        return@singleton RetrofitPrescriptionNetwork(apiService)
    }
}

class RetrofitPrescriptionNetwork(private val networkApi: PrescriptionsService) :
    PrescriptionsDataSource {
    override fun subscribeToPrescriptions(prescriptionSubscribeBody: PrescriptionSubscribeBody): Completable = networkApi.subscribeToPrescriptions(prescriptionSubscribeBody)


}
