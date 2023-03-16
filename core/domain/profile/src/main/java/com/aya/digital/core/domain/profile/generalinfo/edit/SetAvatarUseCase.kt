package com.aya.digital.core.domain.profile.generalinfo.edit

import android.net.Uri
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface SetAvatarUseCase {
    operator fun invoke(imageUri: Uri): Single<RequestResultModel<Boolean>>
}


