package com.aya.digital.core.feature.tabviews.profile.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileState(
    val firstName: String? = null,
    val lastName: String? = null,
    val avatar: String? = null,
    val dateOFBirth: LocalDate? = null,
    val doctorProfile: DoctorProfile?=null
) : BaseState

@Parcelize
data class DoctorProfile(val doctorSpecialities: List<FlavoredProfileModel.DoctorProfileModel.Speciality>):Parcelable