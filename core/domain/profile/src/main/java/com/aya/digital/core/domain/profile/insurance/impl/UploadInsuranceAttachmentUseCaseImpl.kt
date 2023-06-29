package com.aya.digital.core.domain.profile.insurance.impl

import android.net.Uri
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.insurance.UploadInsuranceAttachmentUseCase
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAttachmentUploadModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class UploadInsuranceAttachmentUseCaseImpl(private val profileRepository: ProfileRepository,
                                                    private val progressRepository: ProgressRepository
) : UploadInsuranceAttachmentUseCase {
    override fun invoke(imageUri: Uri): Single<RequestResultModel<InsuranceAttachmentUploadModel>> =
        profileRepository.uploadAttachment(imageUri)
            .trackProgress(progressRepository)
            .mapResult({uploadResult->
                       val uploaded = uploadResult.id!=null && uploadResult.url!=null
                       InsuranceAttachmentUploadModel(uploaded,uploadResult.id,uploadResult.url).asResultModel()
            },{it.toModelError()})
}