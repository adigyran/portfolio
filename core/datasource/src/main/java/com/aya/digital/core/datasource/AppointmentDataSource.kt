package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.response.AppointmentResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.Body

interface AppointmentDataSource {
    fun getAppointments(): Flowable<List<AppointmentResponse>>

    fun createAppointment(slotId: Int, comment: String): Single<AppointmentResponse>
}