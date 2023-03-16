package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.GetInsuranceAttachmentByIdUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAttachmentModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class GetInsuranceAttachmentByIdUseCaseImpl(private val profileRepository: ProfileRepository) : GetInsuranceAttachmentByIdUseCase {
    override fun invoke(imageId: Int): Single<RequestResultModel<InsuranceAttachmentModel>> =
        profileRepository.getAttachmentById(imageId)
            .mapResult({InsuranceAttachmentModel(false,null,null).asResultModel()},{it.toModelError()})
}