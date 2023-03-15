package com.aya.digital.core.domain.profile.insurance.impl

import android.net.Uri
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.insurance.UploadAttachmentUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class UploadAttachmentUseCaseImpl(private val profileRepository: ProfileRepository) : UploadAttachmentUseCase {
    override fun invoke(imageUri: Uri): Single<RequestResultModel<Boolean>> =
        profileRepository.uploadAttachment(imageUri)
            .mapResult({true.asResultModel()},{it.toModelError()})
}