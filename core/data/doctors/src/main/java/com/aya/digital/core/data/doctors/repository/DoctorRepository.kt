package com.aya.digital.core.data.doctors.repository

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.PatientData
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DoctorRepository {
    fun fetchDoctorById(id: Int): Single<RequestResult<DoctorData>>

    fun fetchDoctors(scrollId:String?) : Flowable<RequestResult<PaginationCursorModel<DoctorData>>>

    fun getPatient(patientId:Int):Single<RequestResult<PatientData>>
}