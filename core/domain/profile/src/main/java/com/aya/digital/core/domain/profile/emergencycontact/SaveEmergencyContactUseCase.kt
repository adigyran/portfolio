package com.aya.digital.core.domain.profile.emergencycontact

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactModel
import com.aya.digital.core.domain.profile.insurance.model.InsuranceModel
import io.reactivex.rxjava3.core.Single

interface SaveEmergencyContactUseCase {
    operator fun invoke(name:String, phone:String): Single<RequestResultModel<Boolean>>
}

