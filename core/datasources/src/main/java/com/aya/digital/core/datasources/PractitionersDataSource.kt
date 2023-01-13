package com.aya.digital.core.datasources

import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.doctors.DoctorData
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse
import com.aya.digital.core.network.model.response.doctors.SpecialitiesSpeciality
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PractitionersDataSource {
    
    fun fetchPractitioners(
         page: Int,
         limit: Int,
         search: String?,
         specialty: String?,
         specialtyCode: String?,
         sortingFields: List<String>?,
         sortDirection: String?,
    ): Flowable<PagedResponse<DoctorData>>

    fun fetchPractitionerById(
         id: Int,
    ): Single<DoctorData>

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
    ): Flowable<PagedResponse<SpecialitiesSpeciality>>

    fun fetchSpeciality(
         id: Int,
    ): Single<SpecialitiesSpeciality>
}