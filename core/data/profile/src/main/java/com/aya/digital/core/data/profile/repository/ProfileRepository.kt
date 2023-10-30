package com.aya.digital.core.data.profile.repository

import android.net.Uri
import com.aya.digital.core.data.profile.Address
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProfileRepository {

    fun currentProfileId(): Single<RequestResult<Int>>

    fun currentProfileAvatar(): Single<RequestResult<CurrentProfile.Avatar?>>

    fun currentProfile(): Single<RequestResult<CurrentProfile>>

    fun updateProfile(body: ProfileBody): Single<RequestResult<CurrentProfile>>

    fun getEmergencyContact(): Single<RequestResult<EmergencyContact>>

    fun getEmergencyContacts(): Single<RequestResult<List<EmergencyContact>>>

    fun updateEmergencyContact(id: Int, body: EmergencyContactBody): Single<RequestResult<Boolean>>

    fun createEmergencyContact(body: EmergencyContactBody): Single<RequestResult<Boolean>>
    fun deleteEmergencyContact(id:Int): Single<RequestResult<Boolean>>

    fun uploadAttachment(uri: Uri): Single<RequestResult<ImageUploadResult>>

    fun uploadAvatar(uri: Uri): Single<RequestResult<Boolean>>

    fun getPhoneNumber():Single<RequestResult<String>>
    fun updatePhoneNumber(number: String): Single<RequestResult<Boolean>>
    fun checkIfPhoneVerified():Single<RequestResult<Boolean>>
    fun getPhoneVerificationCode():Single<RequestResult<Boolean>>
    fun sendPhoneVerificationCode(code:String):Single<RequestResult<Boolean>>
    fun getAttachmentById(attachmentId: Int): Single<RequestResult<Boolean>>

    fun getNotificationsStatus():Single<RequestResult<NotificationsStatus>>

    fun updateNotificationStatus(status: NotificationsStatus):Single<RequestResult<NotificationsStatus>>

    fun getAddress():Single<RequestResult<Address>>

    fun saveAddress(addressLine:String):Single<RequestResult<Boolean>>

    fun logout():Single<RequestResult<Boolean>>

    fun clear()


}