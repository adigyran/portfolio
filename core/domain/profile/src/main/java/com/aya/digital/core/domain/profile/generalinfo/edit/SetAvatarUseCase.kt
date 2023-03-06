package com.aya.digital.core.domain.profile.generalinfo.edit

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.generalinfo.edit.model.ProfileEditModel
import io.reactivex.rxjava3.core.Single

interface SetAvatarUseCase {
    operator fun invoke(avatar:String): Single<RequestResultModel<Boolean>>
}


