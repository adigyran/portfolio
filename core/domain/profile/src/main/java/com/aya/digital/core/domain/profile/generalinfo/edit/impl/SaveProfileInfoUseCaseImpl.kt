package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToProfileInfo
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.util.datetime.DateTimeUtils
import io.reactivex.rxjava3.core.Single

internal class SaveProfileInfoUseCaseImpl(private val profileRepository: ProfileRepository,private val dateTimeUtils: DateTimeUtils) :
    SaveProfileInfoUseCase {
    override fun invoke(editModel: ProfileEditModel): Single<RequestResultModel<Boolean>> =
        profileRepository.updateProfile(editModel.toProfileBody())
            .mapResult({true.asResultModel()},{it.toModelError()})
    private fun ProfileEditModel.toProfileBody() = ProfileBody(
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        dateOfBirth = this.dateOfBirth?.let(dateTimeUtils::formatIsoDate),
        sex = this.sex?.tag,
        height = if(this.height.isNullOrBlank()) null else this.height,
        weight = if(this.weight.isNullOrBlank()) null else this.weight,
        ssn = this.ssn,
        shortAddress = this.shortAddress
    )

    private fun deltaSaveProfile(
        newData: ProfileEditModel,
        initialModel: ProfileInfoModel
    ): Pair<Boolean, ProfileEditModel> {
        var shouldSaveProfile = false
        val profileEditModel = ProfileEditModel()
            .apply {
                shouldSaveProfile =
                    newData.firstName.saveField(initialModel.firstName) {
                        firstName = it
                    } || newData.lastName.saveField(initialModel.lastName) { lastName = it }
                            || newData.middleName.saveField(initialModel.middleName) {
                        lastName = it
                    }
                            || newData.dateOfBirth.saveField(initialModel.dateOfBirth) {
                        dateOfBirth = it
                    }
                            || newData.sex.saveField(initialModel.sex) { sex = it }
                            || newData.ssn.saveField(initialModel.ssn) { ssn = it }
                            || newData.tin.saveField(initialModel.tin) { tin = it }
                            || newData.height.saveField(initialModel.height) { height = it }
                            || newData.weight.saveField(initialModel.weight) { weight = it }
                            || newData.shortAddress.saveField(initialModel.shortAddress) {
                        shortAddress = it
                    }

            }
        return Pair(shouldSaveProfile, profileEditModel)
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