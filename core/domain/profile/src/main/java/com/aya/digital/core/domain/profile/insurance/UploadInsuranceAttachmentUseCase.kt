package com.aya.digital.core.domain.profile.insurance

import android.net.Uri
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceAttachmentUploadModel
import io.reactivex.rxjava3.core.Single

interface UploadInsuranceAttachmentUseCase {
    operator fun invoke(imageUri: Uri): Single<RequestResultModel<InsuranceAttachmentUploadModel>>
}



