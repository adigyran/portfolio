package com.aya.digital.core.domain.profile.emergencycontact

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactModel
import io.reactivex.rxjava3.core.Single

interface GetEmergencyContactUseCase {
    operator fun invoke(): Single<RequestResultModel<EmergencyContactModel>>
}

