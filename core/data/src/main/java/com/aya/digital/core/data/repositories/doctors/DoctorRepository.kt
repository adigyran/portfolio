package com.aya.digital.core.data.repositories.doctors

import com.aya.digital.core.data.model.PaginationPageModel
import com.aya.digital.core.data.model.doctors.DoctorData
import com.aya.digital.core.data.model.doctors.Practitioners
import com.aya.digital.core.data.model.doctors.Speciality
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DoctorRepository {
    fun fetchDoctors(
        page: Int,
        specialtyCode: String? = null,
        limit: Int = 10000,
        search: String? = null,
        specialty: String? = null,
        sortingFields: List<String>? = null,
        sortDirection: String? = null,
    ): Flowable<RequestResult<PaginationPageModel<DoctorData>>>

    fun fetchDoctorById(id: Int): Single<RequestResult<DoctorData>>

    fun searchDoctors(searchText: String): Single<RequestResult<Practitioners>>

    fun searchDoctorWithType(
        typeCode: String,
        searchText: String
    ) : Single<RequestResult<Practitioners>>

    fun fetchSpecialities(
    page: Int,
    name: String? = null,
    code: String? = null,
    limit: Int = 20,
    sortingFields: List<String>? = listOf("specialtyName"),
    sortDirection: String = "ASC"
    ): Flowable<RequestResult<PaginationPageModel<Speciality>>>

    fun fetchSpecialityById(id: Int): Single<RequestResult<Speciality>>
}