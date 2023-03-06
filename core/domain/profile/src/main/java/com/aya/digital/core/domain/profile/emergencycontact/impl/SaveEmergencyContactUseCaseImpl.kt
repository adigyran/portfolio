package com.aya.digital.core.domain.profile.emergencycontact.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.emergencycontact.SaveEmergencyContactUseCase
import io.reactivex.rxjava3.core.Single

internal class SaveEmergencyContactUseCaseImpl : SaveEmergencyContactUseCase {
    override fun invoke(name: String, phone: String): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}