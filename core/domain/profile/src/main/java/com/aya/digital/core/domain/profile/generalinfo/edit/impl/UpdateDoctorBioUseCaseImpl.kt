package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorBioUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class UpdateDoctorBioUseCaseImpl(
    private val practitionerRepository: PractitionerRepository,
    private val progressRepository: ProgressRepository
) : UpdateDoctorBioUseCase {
    override fun invoke(bioText: String): Single<RequestResultModel<Boolean>> =
        practitionerRepository
            .saveDoctorBio(bioText)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}