package com.aya.digital.core.domain.doctors.base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class DoctorModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarPhotoLink: String?,
    val bio: String,
    val clinics:List<ClinicModel>,
    val location:LocationModel,
    val postCode:String,
    val city:String,
    val address:String,
    val specialities:List<SpecialityModel>,
    val insurances:List<InsuranceModel>
)
{
    @Parcelize
    data class ClinicModel(
        val clinicName:String
    ):Parcelable
    @Parcelize
    data class LocationModel(
        val long:Double,
        val lat:Double
    ):Parcelable
    @Parcelize
    data class SpecialityModel(
        val name:String
    ):Parcelable

    @Parcelize
    data class InsuranceModel(
        val name:String
    ):Parcelable

}

