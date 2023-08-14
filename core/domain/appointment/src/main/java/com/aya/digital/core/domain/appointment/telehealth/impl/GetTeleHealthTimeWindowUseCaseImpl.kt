package com.aya.digital.core.domain.appointment.telehealth.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthTimeWindowUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetTeleHealthTimeWindowUseCaseImpl(
    private val appointmentRepository: AppointmentRepository,
    private val progressRepository: ProgressRepository
) : GetTeleHealthTimeWindowUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> =
        appointmentRepository.getTimeWindow()
            .doOnSubscribe { progressRepository.setProgress(true) }
            .doOnSuccess { progressRepository.setProgress(false) }
            .mapResult({ it.asResultModel() }, { it.toModelError() })
}/**/