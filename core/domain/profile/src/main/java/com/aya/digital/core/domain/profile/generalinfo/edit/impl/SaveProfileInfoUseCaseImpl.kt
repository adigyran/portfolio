package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.generalinfo.edit.SaveProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import io.reactivex.rxjava3.core.Single

internal class SaveProfileInfoUseCaseImpl : SaveProfileInfoUseCase {
    override fun invoke(editModel: ProfileEditModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }


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
                    } && newData.lastName.saveField(initialModel.lastName) { lastName = it }
                            && newData.middleName.saveField(initialModel.middleName) {
                        lastName = it
                    }
                            && newData.dateOfBirth.saveField(initialModel.dateOfBirth) {
                        dateOfBirth = it
                    }
                            && newData.sex.saveField(initialModel.sex) { sex = it }
                            && newData.height.saveField(initialModel.height) { height = it }
                            && newData.weight.saveField(initialModel.weight) { weight = it }
                            && newData.shortAddress.saveField(initialModel.shortAddress) {
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