package com.aya.digital.core.domain.profile.generalinfo

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.profile.CurrentProfile
import io.reactivex.rxjava3.core.Single

interface GetProfileBriefUseCase {
    operator fun invoke(): Single<RequestResultModel<BriefProfileModel>>
}


data class BriefProfileModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val avatar: String?
) {

}

fun CurrentProfile.mapToBriefProfile() = BriefProfileModel(
    this.id,
    this.email ?: "",
    this.firstName ?: "",
    this.lastName ?: "",
    this.middleName ?: "",
    this.avatar?.fullUrl
)

