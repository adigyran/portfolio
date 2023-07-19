package com.aya.digital.core.domain.base.models.doctors

import android.os.Parcelable
import com.aya.digital.core.data.doctors.DoctorData
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarPhotoLink: String?,
    val bio: String,
    val clinics:List<ClinicModel>,
    val location: LocationModel,
    val postCode:String,
    val city:String,
    val address:String,
    val specialities:List<SpecialityModel>,
    val insurances:List<InsuranceModel>
):Parcelable
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

fun DoctorData.mapToDoctorModel() = DoctorModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    avatarPhotoLink = avatarPhotoLink,
    bio = bio,
    clinics = clinics.map { DoctorModel.ClinicModel(it.name) },
    location = location?.let {
        DoctorModel.LocationModel(
            it.longitude ?: 0.0,
            it.latitude ?: 0.0
        )
    } ?: DoctorModel.LocationModel(0.0, 0.0),
    postCode = postalCode,
    city = city,
    address = address,
    specialities = this.specialities.map { DoctorModel.SpecialityModel(it.specialtyName) },
    insurances = this.insurances.map { com.aya.digital.core.domain.base.models.doctors.DoctorModel.InsuranceModel(it.name) }
)

