package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.PractitionerProfileBody
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.network.model.response.doctors.MedicalDegreeResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.language.LanguageResponse
import com.aya.digital.core.network.model.response.profile.BioResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProfilePractitionerDataSource {

    fun getDoctorInsurances(): Observable<Set<InsuranceCompanyResponse>>

    fun addDoctorInsurances(ids:Set<Int>):Completable

    fun removeDoctorInsurances(ids:Set<Int>):Completable

    fun getDoctorBio():Single<BioResponse>

    fun updateDoctorBio(bioText:String):Completable

    fun getDoctorLanguages():Observable<Set<LanguageResponse>>

    fun addDoctorLanguages(languages:Set<Int>):Completable

    fun removeDoctorLanguages(languages: Set<Int>):Completable

    fun getDoctorSpecialities():Observable<Set<SpecialityResponse>>

    fun addDoctorSpecialities(specialities:Set<Int>):Completable

    fun removeDoctorSpecialities(specialities: Set<Int>):Completable

    fun getDoctorMedicalDegrees():Observable<Set<MedicalDegreeResponse>>

    fun addDoctorMedicalDegrees(medicalDegrees:Set<Int>):Completable

    fun removeDoctorMedicalDegrees(medicalDegrees: Set<Int>):Completable



}