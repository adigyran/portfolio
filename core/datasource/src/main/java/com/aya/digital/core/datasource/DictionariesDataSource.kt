package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

interface DictionariesDataSource {

    fun getInsuranceCompanies(searchTerm:String) : Flowable<PagedResponse<InsuranceBody>>

    fun getInsuranceCompanyById(id:Int) : Single<List<InsuranceBody>>

    fun getInsuranceCompaniesByIds(ids:List<Int>) : Observable<List<InsuranceBody>>
}