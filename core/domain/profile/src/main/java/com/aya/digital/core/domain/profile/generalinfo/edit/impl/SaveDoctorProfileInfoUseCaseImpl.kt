package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorBioUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.UpdateDoctorLanguagesUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.FlavoredProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.domain.profile.insurance.UpdateDoctorInsurancesUseCase
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.util.datetime.DateTimeUtils
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import kotlinx.datetime.toKotlinLocalDate

internal class SaveDoctorProfileInfoUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val updateDoctorBioUseCase: UpdateDoctorBioUseCase,
    private val updateDoctorLanguagesUseCase: UpdateDoctorLanguagesUseCase,
    private val dateTimeUtils: DateTimeUtils,
    private val progressRepository: ProgressRepository
) :
    SaveProfileInfoUseCase {
    override fun invoke(editModel: ProfileEditModel): Single<RequestResultModel<Boolean>> =
        profileRepository.updateProfile(editModel.toProfileBody())
            .trackProgress(progressRepository)
            .flatMapResult({
                val doctorProfileEditModel =
                    editModel.flavoredProfileEditModel as? FlavoredProfileEditModel.DoctorProfileEditModel
                val updateBioUseCase =
                    doctorProfileEditModel?.bio?.let { updateDoctorBioUseCase(it) } ?: Single.just(
                        false.asResultModel()
                    )
                val updateLanguagesUseCase = doctorProfileEditModel?.languages?.let { languages ->
                    updateDoctorLanguagesUseCase(languages.map { it.id }.toSet()).firstOrError()
                } ?: Single.just(false.asResultModel())
                doctorProfileEditModel?.let {
                    Single.zip(updateBioUseCase, updateLanguagesUseCase,
                        BiFunction { t1, t2 ->
                            var updated: Boolean = false
                            t1.processResult({
                                updated = it
                            }, { return@BiFunction it })
                            t2.processResult({ updated = it }, { return@BiFunction it })
                            updated.asResultModel()
                        })
                }
                    ?: Single.just(false.asResultModel())

            }, { Single.just(it.toModelError()) })
            .mapResult({ it.asResultModel() }, { it })

    private fun ProfileEditModel.toProfileBody() = ProfileBody(
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        birthDate = this.dateOfBirth?.toKotlinLocalDate()?.let(dateTimeUtils::formatIsoDate)
    )

    private inline fun <reified T> compareFields(fieldFirst: T?, fieldSecond: T?): T? =
        fieldFirst?.run {
            if (fieldSecond == null) return@run null
            return@run if (fieldSecond == fieldFirst) null else fieldSecond
        }

    private inline fun <reified T> T.saveField(
        initialField: T,
        setField: (newField: T) -> Unit
    ) = compareFields(initialField, this)?.let {
        setField(this)
        return@let true
    } ?: false

}