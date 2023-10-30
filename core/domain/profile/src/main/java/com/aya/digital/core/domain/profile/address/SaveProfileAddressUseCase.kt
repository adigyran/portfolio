package com.aya.digital.core.domain.profile.address

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface SaveProfileAddressUseCase {
    operator fun invoke(addressLine:String): Single<RequestResultModel<Boolean>>
}

