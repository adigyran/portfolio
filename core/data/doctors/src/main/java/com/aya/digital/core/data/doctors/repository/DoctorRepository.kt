package com.aya.digital.core.data.doctors.repository

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.Speciality
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate

interface DoctorRepository {
    fun fetchDoctorById(id: Int): Single<RequestResult<DoctorData>>
}