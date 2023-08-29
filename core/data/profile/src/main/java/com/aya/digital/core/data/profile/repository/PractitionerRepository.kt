package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.profile.Address
import com.aya.digital.core.data.profile.DoctorBio
import com.aya.digital.core.data.profile.practitioner.PractitionerProfile
import com.aya.digital.core.network.model.request.PractitionerProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface PractitionerRepository {
    fun getDoctorBio():Single<RequestResult<DoctorBio>>

    fun saveDoctorBio(bioText:String):Single<RequestResult<Boolean>>

    fun getDoctorLanguages():Observable<RequestResult<Set<LanguageModel>>>

    fun addDoctorLanguages(languages:Set<Int>):Single<RequestResult<Boolean>>

    fun removeDoctorLanguages(languages: Set<Int>):Single<RequestResult<Boolean>>
}