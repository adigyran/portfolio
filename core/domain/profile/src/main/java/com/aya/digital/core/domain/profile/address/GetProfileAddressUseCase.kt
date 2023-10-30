package com.aya.digital.core.domain.profile.address

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.address.model.ProfileAddressModel
import io.reactivex.rxjava3.core.Single

interface GetProfileAddressUseCase {
    operator fun invoke(): Single<RequestResultModel<ProfileAddressModel>>
}

