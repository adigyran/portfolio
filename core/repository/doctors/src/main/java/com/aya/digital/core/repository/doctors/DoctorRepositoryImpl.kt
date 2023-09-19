package com.aya.digital.core.repository.doctors

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.Insurance
import com.aya.digital.core.data.doctors.PatientData
import com.aya.digital.core.data.doctors.mappers.DoctorDataMapper
import com.aya.digital.core.data.doctors.mappers.PatientDataMapper
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.database.dao.DoctorsDao
import com.aya.digital.core.database.model.doctor.asExternalModel
import com.aya.digital.core.database.model.doctor.ext.asEntity
import com.aya.digital.core.database.model.doctor.ext.doctorClinicCrossReferences
import com.aya.digital.core.database.model.doctor.ext.doctorClinicShells
import com.aya.digital.core.database.model.doctor.ext.doctorInsuranceCrossReferences
import com.aya.digital.core.database.model.doctor.ext.doctorInsuranceShells
import com.aya.digital.core.database.model.doctor.ext.doctorLocationShells
import com.aya.digital.core.database.model.doctor.ext.doctorSpecialityCrossReferences
import com.aya.digital.core.database.model.doctor.ext.doctorSpecialityShells
import com.aya.digital.core.datasource.PractitionersDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function4

const val ENABLE_DOCTORS_CACHING = false
internal class DoctorRepositoryImpl(
    private val practitionersDataSource: PractitionersDataSource,
    private val doctorDataMapper: DoctorDataMapper,
    private val patientDataMapper: PatientDataMapper,
    private val doctorsDao: DoctorsDao
) :  DoctorRepository {
    override fun fetchDoctorById(id: Int): Single<RequestResult<DoctorData>> =
        doctorsDao.isDoctorExist(id)
            .flatMap { doctorExist ->
                //TODO temp disable caching
                 return@flatMap loadDoctorFromNetworkAndSave(id)
            }

    private fun loadDoctorFromNetworkAndSave(id: Int) =
        practitionersDataSource.fetchPractitionerById(id)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .flatMapResult({ doctorDataResponse ->
                val doctorData = doctorDataMapper.mapFrom(doctorDataResponse)
                Single.just(doctorData)
                  //  .flatMap { saveData(it) }
                    .map { doctorData.asResult() }

            }, { Single.just(it) })

    private fun saveData(doctorData: DoctorData): Single<Boolean> {
        val zipEntities = Single.zip(
            doctorsDao.insertOrIgnoreDoctor(doctorData.asEntity()),
            doctorsDao.insertOrIgnoreClinics(doctorData.doctorClinicShells()),
            doctorsDao.insertOrIgnoreInsurances(doctorData.doctorInsuranceShells()),
            doctorsDao.insertOrIgnoreSpecialisations(doctorData.doctorSpecialityShells()),
            doctorsDao.insertOrIgnoreLocation(doctorData.doctorLocationShells()),
        ) { _, _, _, _, _ -> true }
        val zipCrossRefs = Single.zip(
            doctorsDao.insertOrIgnoreDoctorClinicCrossRefEntities(doctorData.doctorClinicCrossReferences())
                .andThen(Single.just(true)),
            doctorsDao.insertOrIgnoreDoctorInsuranceCrossRefEntities(doctorData.doctorInsuranceCrossReferences())
                .andThen(Single.just(true)),
            doctorsDao.insertOrIgnoreDoctorSpecialisationCrossRefEntities(doctorData.doctorSpecialityCrossReferences())
                .andThen(Single.just(true))
        ) { c, i, s -> true }
        return Single.zip(zipEntities, zipCrossRefs, { _, _ -> true })
    }


    override fun fetchDoctors(
        lat: Double?,
        long: Double?,
        scrollId: String?,
        specialityCodes: List<Int>?,
        cities: List<String>?,
        insurances: List<Int>?
    ): Flowable<RequestResult<PaginationCursorModel<DoctorData>>> =
        practitionersDataSource.fetchPractitioners(
            lat = lat,
            long = long,
            scrollId = scrollId,
            specialityCodes = specialityCodes,
            cities = cities,
            insurances = insurances
        )
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                PaginationCursorModel(
                    data = doctorDataMapper.mapFromList(result.data),
                    scrollToken = result.scrollToken,
                    sizeResult = result.sizeResult,
                    totalResults = result.totalResults?:0
                ).asResult()
            }, { it })

    override fun getPatient(patientId: Int): Single<RequestResult<PatientData>> =
        practitionersDataSource.getPatient(patientId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                patientDataMapper.mapFrom(result).asResult()
            }, { it })

    override fun addDoctorToFavorites(doctorId: Int): Single<RequestResult<Boolean>> =
        practitionersDataSource.addPractitionerToFavorites(doctorId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })

    override fun removeDoctorFromFavorites(doctorId: Int): Single<RequestResult<Boolean>> =
        practitionersDataSource.removePractitionerFromFavorites(doctorId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })

    override fun getFavoriteDoctors(): Flowable<RequestResult<List<Int>>> =
        practitionersDataSource.getFavoritePractitioners()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.map { result -> result.id }.asResult() }, { it })

    override fun checkDoctorIsFavorite(doctorId: Int): Single<RequestResult<Boolean>> =
        practitionersDataSource.checkPractitionerInFavorite(doctorId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })
}