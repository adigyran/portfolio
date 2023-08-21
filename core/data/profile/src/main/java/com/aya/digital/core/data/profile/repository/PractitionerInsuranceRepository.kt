package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.Address
import com.aya.digital.core.data.profile.practitioner.PractitionerProfile
import com.aya.digital.core.network.model.request.PractitionerProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface PractitionerInsuranceRepository {
    fun getPractitionerInsurances():Observable<RequestResult<Set<Int>>>

    fun addInsurances(ids:Set<Int>):Single<RequestResult<Boolean>>

    fun deleteInsurances(ids: Set<Int>):Single<RequestResult<Boolean>>
}