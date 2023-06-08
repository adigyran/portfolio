package com.aya.digital.core.domain.profile.generalinfo.view

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.model.ProfileSex
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface GetProfileAvatarUseCase {
    operator fun invoke(): Single<RequestResultModel<String?>>
}


