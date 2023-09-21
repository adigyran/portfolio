package com.aya.digital.core.domain.profile.generalinfo.view.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorLanguagesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorSpecialitiesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.toLanguage
import com.aya.digital.core.domain.profile.generalinfo.view.model.toSpeciality
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class GetDoctorSpecialitiesUseCaseImpl(
    private val practitionerRepository: PractitionerRepository,
    private val progressRepository: ProgressRepository
) : GetDoctorSpecialitiesUseCase {
    override fun invoke(): Observable<RequestResultModel<List<FlavoredProfileModel.DoctorProfileModel.Speciality>>> = practitionerRepository
        .getDoctorSpecialities()
        .trackProgress(progressRepository)
        .mapResult({ specialitiesModels -> specialitiesModels.map { it.toSpeciality() }.asResultModel() },
            { it.toModelError() })
}