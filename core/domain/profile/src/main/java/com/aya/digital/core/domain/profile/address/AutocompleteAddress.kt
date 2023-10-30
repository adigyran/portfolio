package com.aya.digital.core.domain.profile.address

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface AutocompleteAddress {
    operator fun invoke(address:String): Single<RequestResultModel<Boolean>>
}

