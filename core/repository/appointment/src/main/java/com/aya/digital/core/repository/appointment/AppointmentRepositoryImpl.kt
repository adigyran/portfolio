package com.aya.digital.core.repository.appointment

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.datasource.AppointmentDataSource
import com.aya.digital.core.datasource.TeleHealthDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

internal class AppointmentRepositoryImpl(private val appointmentDataSource: AppointmentDataSource,private val teleHealthDataSource: TeleHealthDataSource, private val appointmentMapper: AppointmentMapper):AppointmentRepository {
    override fun getRoomTokenById(roomId: Int): Single<RequestResult<String>> =
        teleHealthDataSource.getRoomToken(GetTelehealthRoomTokenBody("$roomId"))
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({result-> result.token.asResult()},{it})

    override fun getAppointments(): Flowable<RequestResult<List<Appointment>>> =
        appointmentDataSource.getAppointments()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({result-> appointmentMapper.mapFromList(result).asResult()   },{it})
}