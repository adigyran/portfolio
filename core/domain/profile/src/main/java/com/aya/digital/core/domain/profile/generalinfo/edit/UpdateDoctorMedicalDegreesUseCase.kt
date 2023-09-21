package com.aya.digital.core.domain.profile.generalinfo.edit

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.insurance.model.InsurancePolicyItemModel
import io.reactivex.rxjava3.core.Observable

interface UpdateDoctorMedicalDegreesUseCase {
    operator fun invoke(ids:Set<Int>): Observable<RequestResultModel<Boolean>>
}

