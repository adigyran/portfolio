package com.aya.digital.core.domain.profile.security.changephone

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface ChangePhoneUseCase {
    operator fun invoke(newPhone:String): Single<RequestResultModel<Boolean>>
}