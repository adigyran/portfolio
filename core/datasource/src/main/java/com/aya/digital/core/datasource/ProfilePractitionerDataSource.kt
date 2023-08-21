package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.PractitionerProfileBody
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.network.model.response.profile.AddressResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProfilePractitionerDataSource {

    fun getDoctorInsurances(): Observable<List<Int>>

    fun addDoctorInsurances(ids:List<Int>):Single<List<Int>>

    fun removeDoctorInsurances(ids:List<Int>):Single<Unit>

}