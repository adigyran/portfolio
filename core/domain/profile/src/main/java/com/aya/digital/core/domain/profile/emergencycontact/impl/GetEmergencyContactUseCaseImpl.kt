package com.aya.digital.core.domain.profile.emergencycontact.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.emergencycontact.GetEmergencyContactUseCase
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactModel
import io.reactivex.rxjava3.core.Single

internal class GetEmergencyContactUseCaseImpl : GetEmergencyContactUseCase {
    override fun invoke(): Single<RequestResultModel<EmergencyContactModel>> {
        TODO("Not yet implemented")
    }
}