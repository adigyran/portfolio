package com.aya.digital.core.domain.profile.generalinfo.edit

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import io.reactivex.rxjava3.core.Single

interface SaveProfileInfoUseCase {
    operator fun invoke(editModel: ProfileEditModel): Single<RequestResultModel<Boolean>>
}


