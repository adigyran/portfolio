package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import android.net.Uri
import android.os.Parcelable
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileGeneralInfoEditState(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val patientFields: PatientFields? = null,
    val doctorFields: DoctorFields? = null,
    val avatarUri:Uri? = null,
    val avatarUrl:String = ""
) : BaseState


@Parcelize
data class PatientFields(
    val sex: ProfileSex? = null,
    val height: String? = null,
    val heightError: String? = null,
    val weight: String? = null,
    val weightError: String? = null,
    val shortAddress: String? = null,
    val ssnOrTin: String? = null,
    val ssnOrTinError: String? = null,
): Parcelable

@Parcelize
data class DoctorFields(
    val npi: String? = null,
    val npiError:String? = null,
    val tin: String? = null,
    val tinError:String? = null,
    val licenseNumber: String? = null,
    val licenseNumberError:String? = null,
    val languages: String? = null,
    val languagesError:String? = null,
    val bio: String? = null,
    val bioError: String? = null,
): Parcelable