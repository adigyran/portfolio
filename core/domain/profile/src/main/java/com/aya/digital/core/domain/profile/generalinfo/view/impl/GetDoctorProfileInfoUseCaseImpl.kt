package com.aya.digital.core.domain.profile.generalinfo.view.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.flatMapResult
import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorBioUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorLanguagesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToProfileInfo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

internal class GetDoctorProfileInfoUseCaseImpl(
    private val getDoctorBioUseCase: GetDoctorBioUseCase,
    private val getDoctorLanguagesUseCase: GetDoctorLanguagesUseCase,
    private val getProfileBriefUseCase: GetProfileBriefUseCase,
    private val progressRepository: ProgressRepository
) :
    GetProfileInfoUseCase {
    override fun invoke(): Single<RequestResultModel<ProfileInfoModel>> =
        getProfileBriefUseCase()
            .trackProgress(progressRepository)
            .flatMapResult({ profile ->
                val profileInfo = profile.mapToProfileInfo()
                Single.zip(
                    getDoctorBioUseCase(),
                    getDoctorLanguagesUseCase().firstOrError(),
                    BiFunction { t1, t2 ->
                        val doctorProfileModel = FlavoredProfileModel.DoctorProfileModel()
                        t1.processResult({ doctorBio -> doctorProfileModel.bio = doctorBio },
                            { it })
                        t2.processResult({ doctorLanguages ->
                            doctorProfileModel.languages = doctorLanguages
                        }, { it })
                        return@BiFunction doctorProfileModel.asResultModel()
                    })
                    .mapResult({ doctorProfileModel ->
                        profileInfo.apply { flavoredProfileModel = doctorProfileModel }
                            .asResultModel()
                    }, { it })

            }, { Single.just(it) })
}