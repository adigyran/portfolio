package com.aya.digital.core.datasource.network

import com.aya.digital.core.network.api.services.AppointmentService
import com.aya.digital.core.network.main.di.modules.createApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun appointmentNetworkModule() = DI.Module("appointmentNetworkModule") {
    bind<com.aya.digital.core.datasource.AppointmentDataSource>() with singleton {
        val apiService =
            createApiService<AppointmentService>(instance())
        return@singleton RetrofitAppointmentNetwork(apiService)
    }
}

class RetrofitAppointmentNetwork(private val networkApi: com.aya.digital.core.network.api.services.AppointmentService) :
    com.aya.digital.core.datasource.AppointmentDataSource {

    override fun getAppointment(id: Int): Single<com.aya.digital.core.network.model.response.AppointmentResponse> =
        networkApi.getAppointment(id)

    override fun getAppointments(
        patient: Int?,
        practitioner: Int?,
        slot: Int?,
        page: Int,
        limit: Int,
        period: String?,
        start: LocalDate?,
        end: LocalDate?
    ): Observable<com.aya.digital.core.network.model.response.base.PagedResponse<com.aya.digital.core.network.model.response.AppointmentResponse>> =
        networkApi.getAppointments(patient, practitioner, slot, page, limit, period, start, end)

    override fun createAppointment(body: com.aya.digital.core.network.model.request.CreateAppointmentBody): Single<com.aya.digital.core.network.model.response.AppointmentResponse> =
        networkApi.createAppointment(body)

    override fun rescheduleAppointment(appointmentId: Int, slotId: Int): Completable =
        networkApi.rescheduleAppointment(appointmentId, slotId)

    override fun repeatAppointment(body: com.aya.digital.core.network.model.request.RepeatAppointmentBody): Single<com.aya.digital.core.network.model.response.AppointmentResponse> =
        networkApi.repeatAppointment(body)

    override fun cancelAppointment(appointmentId: Int): Completable =
        networkApi.cancelAppointment(appointmentId)
}
