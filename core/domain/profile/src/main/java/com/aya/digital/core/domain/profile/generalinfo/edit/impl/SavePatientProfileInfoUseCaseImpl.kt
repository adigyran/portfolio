package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.FlavoredProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToProfileInfo
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.util.datetime.DateTimeUtils
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.toKotlinLocalDate

internal class SavePatientProfileInfoUseCaseImpl(
    private val profileRepository: ProfileRepository, private val dateTimeUtils: DateTimeUtils,
    private val progressRepository: ProgressRepository
) :
    SaveProfileInfoUseCase {
    override fun invoke(editModel: ProfileEditModel): Single<RequestResultModel<Boolean>> =
        profileRepository.updateProfile(editModel.toProfileBody())
            .trackProgress(progressRepository)
            .mapResult({ true.asResultModel() }, { it.toModelError() })


    private fun ProfileEditModel.toProfileBody() = kotlin.run {
        val patientProfileEditModel =
            flavoredProfileEditModel as? FlavoredProfileEditModel.PatientProfileEditModel
        return@run ProfileBody(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            birthDate = this.dateOfBirth?.toKotlinLocalDate()?.let(dateTimeUtils::formatIsoDate),
            sex = patientProfileEditModel?.sex?.tag,
            height = patientProfileEditModel?.height,
            weight = patientProfileEditModel?.weight,
            ssn = patientProfileEditModel?.ssn,
            shortAddress = patientProfileEditModel?.shortAddress
        )
    }

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