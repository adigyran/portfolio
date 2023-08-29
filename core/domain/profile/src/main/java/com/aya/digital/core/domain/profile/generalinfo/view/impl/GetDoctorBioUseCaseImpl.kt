package com.aya.digital.core.domain.profile.generalinfo.view.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.DoctorBio
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorBioUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetDoctorBioUseCaseImpl(
    private val practitionerRepository: PractitionerRepository,
    private val progressRepository: ProgressRepository
) : GetDoctorBioUseCase {
    override fun invoke(): Single<RequestResultModel<String?>> = practitionerRepository
        .getDoctorBio()
        .trackProgress(progressRepository)
        .mapResult({ it.aboutInfo.asResultModel() }, { it.toModelError() })

}