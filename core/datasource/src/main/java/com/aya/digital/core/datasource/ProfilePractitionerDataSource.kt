package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.PractitionerProfileBody
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.network.model.response.profile.AddressResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ProfilePractitionerDataSource {

    fun currentPractitioner(): Single<PractitionerProfileResponse>

    fun getPractitionerPhoneNumber(): Single<PractitionerProfileResponse>

    fun updatePractitioner(
        body: PractitionerProfileBody
    ): Completable

    fun updatePractitionerPhoneNumber(
        body: PractitionerProfileBody
    ): Completable

    fun updatePractitionerAddress(
        body: PractitionerProfileBody
    ): Completable

    fun getPractitionerAddress(): Single<AddressResponse>
}