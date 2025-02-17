package com.aya.digital.core.repository.appointment

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.TelehealthWaitTimeModel
import com.aya.digital.core.data.appointment.mappers.AppointmentMapper
import com.aya.digital.core.data.appointment.mappers.TelehealthWaitTimeMapper
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
import java.time.LocalDateTime
import java.time.ZoneId

internal class AppointmentRepositoryImpl(
    private val appointmentDataSource: AppointmentDataSource,
    private val teleHealthDataSource: TeleHealthDataSource,
    private val appointmentMapper: AppointmentMapper,
    private val telehealthWaitTimeMapper: TelehealthWaitTimeMapper
) : AppointmentRepository {
    override fun getRoomTokenById(roomId: Int): Single<RequestResult<String>> =
        teleHealthDataSource.getRoomToken(GetTelehealthRoomTokenBody("$roomId"))
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> result.token.asResult() }, { it })

    override fun getTimeWindow(): Single<RequestResult<TelehealthWaitTimeModel>> = teleHealthDataSource
        .getTimeWindow()
        .retryOnError()
        .retrofitResponseToResult(CommonUtils::mapServerErrors)
        .mapResult({ result -> telehealthWaitTimeMapper.mapFrom(result).asResult() }, { it })

    override fun getAppointments(
        start: LocalDateTime,
        end: LocalDateTime
    ): Flowable<RequestResult<List<Appointment>>> =
        appointmentDataSource.getAppointments(
            start = start.atZone(ZoneId.systemDefault()).toInstant().toString(),
            end = end.atZone(ZoneId.systemDefault()).toInstant().toString()
        )
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> appointmentMapper.mapFromList(result).asResult() }, { it })

    override fun getAppointmentById(appointmentId: Int): Single<RequestResult<Appointment>> =
        appointmentDataSource.getAppointmentById(appointmentId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> appointmentMapper.mapFrom(result).asResult() }, { it })

    override fun cancelAppointment(appointmentId: Int): Single<RequestResult<Boolean>> =
        appointmentDataSource.cancelAppointment(appointmentId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)

    override fun createAppointment(
        slotId: Int,
        comment: String
    ): Single<RequestResult<Appointment>> =
        appointmentDataSource.createAppointment(slotId, comment)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> appointmentMapper.mapFrom(result).asResult() }, { it })
}