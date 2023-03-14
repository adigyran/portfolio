package com.aya.digital.core.domain.profile.attachment

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import io.reactivex.rxjava3.core.Single

interface GetAttachmentByIdUseCase {
    operator fun invoke(id:Int): Single<RequestResultModel<Boolean>>
}

