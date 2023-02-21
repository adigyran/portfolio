package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenDataSource {

    fun refreshToken(refreshTokenBody: RefreshTokenBody): Completable

    fun logout(logoutBody: LogoutBody): Completable
}