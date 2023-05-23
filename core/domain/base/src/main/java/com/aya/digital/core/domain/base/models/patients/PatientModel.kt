package com.aya.digital.core.domain.base.models.patients

import android.os.Parcelable
import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.PatientData
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/*   val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarPhotoLink:String?,
    val birthDate:LocalDate?,
    val insurances:List<Insurance>,*/

@Parcelize
data class PatientModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarPhotoLink: String?,
    val birthDate:@RawValue LocalDate?,
    val insurances:List<InsuranceModel>
):Parcelable
{
    @Parcelize
    data class InsuranceModel(
        val name:String
    ):Parcelable

}

fun PatientData.mapToPatientModel() = PatientModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    avatarPhotoLink = avatarPhotoLink,
    birthDate = birthDate,
    insurances = insurances.map { insurance -> PatientModel.InsuranceModel(insurance.name) }
)

