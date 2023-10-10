package com.aya.digital.core.domain.profile.emergencycontact.operations

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface DeleteEmergencyContactUseCase {
    operator fun invoke(id:Int): Single<RequestResultModel<Boolean>>
}

