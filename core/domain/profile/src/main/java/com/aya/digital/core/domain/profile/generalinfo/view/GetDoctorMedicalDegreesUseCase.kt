package com.aya.digital.core.domain.profile.generalinfo.view

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import io.reactivex.rxjava3.core.Observable

import io.reactivex.rxjava3.core.Single

interface GetDoctorMedicalDegreesUseCase {
    operator fun invoke(): Observable<RequestResultModel<List<FlavoredProfileModel.DoctorProfileModel.Degree>>>
}


