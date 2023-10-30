package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.CityResponse
import com.aya.digital.core.network.model.response.doctors.MedicalDegreeResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.emergencycontact.EmergencyContactTypeResponse
import com.aya.digital.core.network.model.response.language.LanguageResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DictionariesDataSource {

    fun getInsuranceCompanies(searchTerm:String?,selectedIds:List<Int>,cursor:String?) : Flowable<PagedCursorResponse<InsuranceCompanyResponse>>

    fun getInsuranceCompanyById(id:Int) : Single<PagedCursorResponse<InsuranceCompanyResponse>>

    fun getInsuranceCompaniesByIds(ids:List<Int>) : Observable<PagedCursorResponse<InsuranceCompanyResponse>>

    fun getSpecialisations(searchTerm:String?,selectedIds:List<Int>,cursor:String?) : Flowable<PagedCursorResponse<SpecialityResponse>>

    fun getCities(searchTerm: String?,selectedIds:List<Int>,cursor:String?): Flowable<PagedCursorResponse<CityResponse.CityContent>>

    fun getLanguages(searchTerm: String?,selectedIds:List<Int>,cursor:String?):Flowable<PagedCursorResponse<LanguageResponse>>
    fun getMedicalDegrees(searchTerm: String?,selectedIds:List<Int>,cursor:String?):Flowable<PagedCursorResponse<MedicalDegreeResponse>>

    fun getEmergencyContactsTypes(searchTerm: String?,selectedIds:List<Int>,cursor:String?):Flowable<PagedCursorResponse<EmergencyContactTypeResponse>>
    fun getEmergencyContactsTypeById(id: Int):Single<EmergencyContactTypeResponse>



}