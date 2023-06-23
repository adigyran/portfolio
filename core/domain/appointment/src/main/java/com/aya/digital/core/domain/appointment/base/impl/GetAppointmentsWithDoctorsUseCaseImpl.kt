package com.aya.digital.core.domain.appointment.base.impl

import android.annotation.SuppressLint
import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.base.models.appointment.toAppointmentModel
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.functions.BiConsumer
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber

internal class GetAppointmentsWithDoctorsUseCaseImpl(
    private val appointmentRepository: AppointmentRepository,
    private val doctorRepository: DoctorRepository
) : GetAppointmentsWithParticipantsUseCase {
    @SuppressLint("CheckResult")
    override fun invoke(date: LocalDate?): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>> {
        var startDateTime:LocalDateTime = LocalDateTime(0,2,1,0,0,0)
        var endDateTime:LocalDateTime =  LocalDateTime(0,2,1,0,0,0)
        val systemTZ = TimeZone.currentSystemDefault()
        if(date!=null)
        {
            val atStartOfDayIn = date.atStartOfDayIn(systemTZ)
            startDateTime = atStartOfDayIn.toLocalDateTime(systemTZ)
            endDateTime= atStartOfDayIn.plus(1, DateTimeUnit.DAY, systemTZ).toLocalDateTime(systemTZ)
        }
        else
        {
            val currentInstant = Clock.System.now()
            startDateTime = currentInstant.toLocalDateTime(systemTZ)
            endDateTime =
                currentInstant.plus(100, DateTimeUnit.DAY, systemTZ).toLocalDateTime(systemTZ)
        }
        return appointmentRepository.getAppointments(startDateTime, endDateTime)
            .flatMapResult({ appointments ->
                return@flatMapResult Flowable.fromIterable(appointments)
                    .concatMap { appointment ->
                        val doctorId = appointment.participant!!.id
                        doctorRepository.fetchDoctorById(doctorId)
                            .mapResult({ doctorData ->
                                AppointmentWithParticipantModel(
                                    appointmentModel = appointment.toAppointmentModel(),
                                    doctorModel = doctorData.mapToDoctorModel()
                                ).asResult()
                            }, { it })
                            .doAfterSuccess { Timber.d("$it") }
                            .toFlowable()
                    }
                    .collectInto(
                        mutableListOf<AppointmentWithParticipantModel>(),
                        BiConsumer { t1, t2 ->
                            t2.processResult({ t1.add(it) }, {})
                        })
                    .map { it.asResult() }
                    .toFlowable()
            }, { Flowable.just(it) })

            .mapResult({ it.asResultModel() }, { it.toModelError() })

    }
}