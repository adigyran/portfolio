package com.aya.digital.core.data.dictionaries.repository

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.dictionaries.CityModel
import com.aya.digital.core.data.dictionaries.EmergencyContactTypeModel
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.MedicalDegreeModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DictionariesRepository {
    //insurance companies
    fun getInsuranceCompanies(searchTerm:String?,selectedIds:List<Int>, cursor:String?): Flowable<RequestResult<PaginationCursorModel<InsuranceCompanyModel>>>
    fun getInsuranceCompanyById(id:Int):Single<RequestResult<InsuranceCompanyModel>>
    fun getInsuranceCompaniesByIds(ids:List<Int>) : Observable<RequestResult<Set<InsuranceCompanyModel>>>
    //specialities
    fun getSpecialities(searchTerm: String?,selectedIds:List<Int>,cursor:String?):Flowable<RequestResult<PaginationCursorModel<SpecialityModel>>>

    fun getCities(searchTerm: String?,selectedIds:List<Int>, cursor:String?): Flowable<RequestResult<PaginationCursorModel<CityModel>>>

    fun getLanguages(searchTerm: String?,selectedIds:List<Int>, cursor:String?): Flowable<RequestResult<PaginationCursorModel<LanguageModel>>>

    fun getMedicalDegrees(searchTerm: String?,selectedIds:List<Int>, cursor:String?): Flowable<RequestResult<PaginationCursorModel<MedicalDegreeModel>>>

    fun getEmergencyContactTypeById(id:Int): Single<RequestResult<EmergencyContactTypeModel>>

    fun getEmergencyContactTypes(searchTerm: String?, selectedIds:List<Int>, cursor:String?): Flowable<RequestResult<PaginationCursorModel<EmergencyContactTypeModel>>>

}