package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.LocalDate

internal class GetAppointmentsWithPatientsUseCaseImpl(private val appointmentRepository: AppointmentRepository,
                                             private val doctorRepository: DoctorRepository
) : GetAppointmentsWithParticipantsUseCase {
    override fun invoke(date: LocalDate?): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>> {
        TODO("Not yet implemented")
    }
}