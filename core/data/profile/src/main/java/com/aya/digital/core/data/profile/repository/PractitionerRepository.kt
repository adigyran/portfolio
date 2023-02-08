package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.Address
import com.aya.digital.core.data.profile.practitioner.PractitionerProfile
import com.aya.digital.core.network.model.request.PractitionerProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface PractitionerRepository {

    fun getPractitioner(): Single<RequestResult<PractitionerProfile>>

    fun updatePractitioner(body: com.aya.digital.core.network.model.request.PractitionerProfileBody): Single<RequestResult<Unit>>

    fun updatePractitionerAddress(body: com.aya.digital.core.network.model.request.PractitionerProfileBody): Single<RequestResult<Unit>>

    fun getPractitionerAddress(): Single<RequestResult<Address>>

    fun getPractitionerPhoneNumber(): Single<RequestResult<PractitionerProfile>>
}