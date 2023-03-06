package com.aya.digital.core.domain.profile.generalinfo.edit

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import io.reactivex.rxjava3.core.Single

internal class SaveProfileInfoUseCaseImpl : SaveProfileInfoUseCase {
    override fun invoke(editModel: ProfileEditModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}