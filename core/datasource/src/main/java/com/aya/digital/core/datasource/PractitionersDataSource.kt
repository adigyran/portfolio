package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface PractitionersDataSource {
    
    fun fetchPractitioners(
         page: Int,
         limit: Int,
         search: String?,
         specialty: String?,
         specialtyCode: String?,
         sortingFields: List<String>?,
         sortDirection: String?,
    ): Flowable<PagedResponse<DoctorDataResponse>>

    fun fetchPractitionerById(
         id: Int,
    ): Single<DoctorDataResponse>

    fun searchPractitioners(
         search: String,
         page: Int = 0,
         limit: Int = 10
    ): Flowable<PractitionersResponse>

    
    fun searchPractitionersWithCode(
         code: String,
         search: String,
         page: Int = 0,
         limit: Int = 10
    ): Flowable<PractitionersResponse>

    fun fetchSpecialities(
         page: Int,
         limit: Int,
         sortingFields: List<String>?,
         sortDirection: String?,
         name: String?,
    ): Flowable<PagedResponse<SpecialityResponse>>

    fun fetchSpeciality(
         id: Int,
    ): Single<SpecialityResponse>
}