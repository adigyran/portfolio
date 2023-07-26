package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.patient.PatientDataResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface PractitionersDataSource {

    fun fetchPractitioners(
        lat: Double?,
        long: Double?,
        scrollId: String?,
        specialityCodes: List<Int>?,
        cities: List<String>?,
        insurances: List<Int>?
    ): Flowable<PagedCursorResponse<DoctorDataResponse>>

    fun fetchPractitionerById(
        id: Int,
    ): Single<DoctorDataResponse>

    fun fetchSpecialities(
        page: Int,
        limit: Int,
        sortingFields: List<String>?,
        sortDirection: String?,
        name: String?,
    ): Flowable<PagedCursorResponse<SpecialityResponse>>

    fun fetchSpeciality(
        id: Int,
    ): Single<SpecialityResponse>

    fun getPatient(
        id: Int
    ): Single<PatientDataResponse>

    fun addPractitionerToFavorites(id:Int):Single<Boolean>
    fun removePractitionerFromFavorites(id:Int):Single<Boolean>
    fun getFavoritePractitioners():Flowable<List<DoctorDataResponse>>
    fun checkPractitionerInFavorite(id: Int):Single<Boolean>
}