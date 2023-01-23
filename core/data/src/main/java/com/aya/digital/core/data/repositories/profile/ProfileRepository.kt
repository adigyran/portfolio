package com.aya.digital.core.data.repositories.profile

import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.data.model.profile.EmergencyContact
import com.aya.digital.core.data.model.profile.ImageUploadResult
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import java.io.File

interface ProfileRepository {

    fun currentProfileId(): Single<RequestResult<Int>>

    fun currentProfile(): Single<RequestResult<CurrentProfile>>

    fun updateProfile(body: ProfileBody): Single<RequestResult<Unit>>

    fun getEmergencyContact(): Single<RequestResult<EmergencyContact>>

    fun updateEmergencyContact(body: EmergencyContactBody): Single<RequestResult<Unit>>

    fun uploadAvatar(file: File): RequestResult<ImageUploadResult>

    fun updatePhoneNumber(number: String): Single<RequestResult<Unit>>

    fun deleteAvatar(): Single<RequestResult<Unit>>

    fun clear()
}