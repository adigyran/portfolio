package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.profile.EmergencyContactResponse
import com.aya.digital.core.network.model.response.MessageResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

interface ProfileDataSource {


    fun currentProfile(): Single<CurrentProfileResponse>

    fun updateProfile(
        body: ProfileBody
    ): Completable

    fun getEmergencyContact(): Single<EmergencyContactResponse>

    fun updateEmergencyContact(
        body: EmergencyContactBody
    ): Completable

    fun uploadAvatar(
        file: MultipartBody.Part
    ): Single<ImageUploadResponse>

    fun deleteAvatar(): Completable

    fun registration(body: RegistrationBody): Single<MessageResponse>

    fun logout(
        clientId: String,
        refreshToken: String,
    ): Completable
}