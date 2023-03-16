package com.aya.digital.core.domain.profile.insurance

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAttachmentModel
import io.reactivex.rxjava3.core.Single

interface GetInsuranceAttachmentByIdUseCase {
    operator fun invoke(imageId:Int): Single<RequestResultModel<InsuranceAttachmentModel>>
}



