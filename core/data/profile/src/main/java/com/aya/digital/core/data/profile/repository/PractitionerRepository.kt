package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.MedicalDegreeModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
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

    fun getDoctorMedicalDegrees():Observable<RequestResult<Set<MedicalDegreeModel>>>

    fun addDoctorMedicalDegrees(degrees:Set<Int>):Single<RequestResult<Boolean>>

    fun removeDoctorMedicalDegrees(degrees: Set<Int>):Single<RequestResult<Boolean>>

    fun getDoctorSpecialities():Observable<RequestResult<Set<SpecialityModel>>>

    fun addDoctorSpecialities(specialities:Set<Int>):Single<RequestResult<Boolean>>

    fun removeDoctorSpecialities(specialities: Set<Int>):Single<RequestResult<Boolean>>
}