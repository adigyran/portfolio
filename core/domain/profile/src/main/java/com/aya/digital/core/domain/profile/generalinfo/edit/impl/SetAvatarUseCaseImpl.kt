package com.aya.digital.core.domain.profile.generalinfo.edit.impl

import android.net.Uri
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.edit.SetAvatarUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class SetAvatarUseCaseImpl(private val profileRepository: ProfileRepository,
                           private val progressRepository: ProgressRepository
) : SetAvatarUseCase {
    override fun invoke(imageUri: Uri): Single<RequestResultModel<Boolean>> =
        profileRepository.uploadAvatar(imageUri)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},{it.toModelError()})
}