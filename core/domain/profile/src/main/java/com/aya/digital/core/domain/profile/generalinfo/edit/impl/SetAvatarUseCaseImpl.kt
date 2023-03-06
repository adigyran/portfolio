package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import io.reactivex.rxjava3.core.Single

class SetAvatarUseCaseImpl : SetAvatarUseCase {
    override fun invoke(avatar: String): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}