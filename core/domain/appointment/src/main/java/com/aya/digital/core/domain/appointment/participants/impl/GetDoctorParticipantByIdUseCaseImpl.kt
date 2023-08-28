package com.aya.digital.core.domain.appointment.participants.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.participants.GetParticipantByIdUseCase
import com.aya.digital.core.domain.appointment.participants.model.AppointmentDoctorParticipant
import com.aya.digital.core.domain.appointment.participants.model.AppointmentParticipant
import com.aya.digital.core.domain.appointment.participants.model.mapToDoctorParticipant
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetDoctorParticipantByIdUseCaseImpl(
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) : GetParticipantByIdUseCase {
    override fun invoke(participantId: Int): Single<RequestResultModel<AppointmentParticipant>> =
        doctorRepository.fetchDoctorById(participantId)
            .trackProgress(progressRepository)
            .mapResult({ doctorData -> doctorData.mapToDoctorModel().mapToDoctorParticipant().asResultModel()
            }, { it.toModelError() })
}