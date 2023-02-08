package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import java.io.File

interface ProfileRepository {

    fun currentProfileId(): Single<RequestResult<Int>>

    fun currentProfile(): Single<RequestResult<CurrentProfile>>

    fun updateProfile(body: com.aya.digital.core.network.model.request.ProfileBody): Single<RequestResult<Unit>>

    fun getEmergencyContact(): Single<RequestResult<EmergencyContact>>

    fun updateEmergencyContact(body: com.aya.digital.core.network.model.request.EmergencyContactBody): Single<RequestResult<Unit>>

    fun uploadAvatar(file: File): RequestResult<ImageUploadResult>

    fun updatePhoneNumber(number: String): Single<RequestResult<Unit>>

    fun deleteAvatar(): Single<RequestResult<Unit>>

    fun clear()
}