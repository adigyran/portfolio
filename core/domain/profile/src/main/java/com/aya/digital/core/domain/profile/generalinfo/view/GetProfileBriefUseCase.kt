package com.aya.digital.core.domain.profile.generalinfo.view

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.domain.profile.generalinfo.view.model.BriefProfileModel
import io.reactivex.rxjava3.core.Single

interface GetProfileBriefUseCase {
    operator fun invoke(): Single<RequestResultModel<BriefProfileModel>>
}



