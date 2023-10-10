package com.aya.digital.core.domain.profile.emergencycontact.operations

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface CreateEmergencyContactUseCase {
    operator fun invoke(name:String, phone:String,summary:String,type:Int): Single<RequestResultModel<Boolean>>
}

