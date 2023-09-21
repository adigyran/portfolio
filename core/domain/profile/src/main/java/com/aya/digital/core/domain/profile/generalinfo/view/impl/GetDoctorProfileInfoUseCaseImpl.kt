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
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorMedicalDegreesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetDoctorSpecialitiesUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToProfileInfo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function4

internal class GetDoctorProfileInfoUseCaseImpl(
    private val getDoctorBioUseCase: GetDoctorBioUseCase,
    private val getDoctorLanguagesUseCase: GetDoctorLanguagesUseCase,
    private val getDoctorMedicalDegreesUseCase: GetDoctorMedicalDegreesUseCase,
    private val getDoctorSpecialitiesUseCase: GetDoctorSpecialitiesUseCase,
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
                    getDoctorSpecialitiesUseCase().firstOrError(),
                    getDoctorMedicalDegreesUseCase().firstOrError(),
                    Function4 { t1, t2, t3, t4 ->
                        val doctorProfileModel = FlavoredProfileModel.DoctorProfileModel()
                        t1.processResult({ doctorBio -> doctorProfileModel.bio = doctorBio },
                            { it })
                        t2.processResult({ doctorLanguages ->
                            doctorProfileModel.languages = doctorLanguages
                        }, { it })
                        t3.processResult({ doctorSpecialities ->
                            doctorProfileModel.specialities = doctorSpecialities
                        }, { it })
                        t4.processResult({ doctorDegrees ->
                            doctorProfileModel.degrees = doctorDegrees
                        }, { it })
                        return@Function4 doctorProfileModel.asResultModel()
                    })
                    .mapResult({ doctorProfileModel ->
                        profileInfo.apply { flavoredProfileModel = doctorProfileModel }
                            .asResultModel()
                    }, { it })

            }, { Single.just(it) })
}