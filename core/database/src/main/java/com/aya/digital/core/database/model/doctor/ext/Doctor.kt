package com.aya.digital.core.database.model.doctor.ext

import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.Insurance
import com.aya.digital.core.database.model.doctor.DoctorClinicCrossRef
import com.aya.digital.core.database.model.doctor.DoctorClinicEntity
import com.aya.digital.core.database.model.doctor.DoctorEntity
import com.aya.digital.core.database.model.doctor.DoctorInsuranceCrossRef
import com.aya.digital.core.database.model.doctor.DoctorLocationEntity
import com.aya.digital.core.database.model.doctor.DoctorSpecialisationCrossRef
import com.aya.digital.core.database.model.doctor.DoctorSpecialisationEntity
import com.aya.digital.core.database.model.doctor.InsuranceEntity

fun DoctorData.asEntity() = DoctorEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    avatarPhotoLink = avatarPhotoLink,
    bio = bio,
    city = city,
    address = address,
    postalCode = postalCode
)


fun DoctorData.doctorLocationShells() =
    location?.run {
        DoctorLocationEntity(
            doctorId = id,
            latitude = latitude,
            longitude = longitude ?: 0.0
        )
    }

fun DoctorData.doctorInsuranceShells() =
    insurances.map { insurance ->
        InsuranceEntity(
            id = insurance.id,
            name = insurance.name
        )
    }

fun DoctorData.doctorInsuranceCrossReferences() =
    insurances.map { insurance ->
        DoctorInsuranceCrossRef(
            doctorId = id,
            insuranceId = insurance.id
        )
    }

fun DoctorData.doctorClinicShells() =
    clinics.map { clinic ->
        DoctorClinicEntity(
            id = clinic.id,
            name = clinic.name
        )
    }

fun DoctorData.doctorClinicCrossReferences() =
    clinics.map { clinic ->
        DoctorClinicCrossRef(
            doctorId = id,
            clinicId = clinic.id
        )
    }

fun DoctorData.doctorSpecialityShells() =
    specialities.map { speciality ->
        DoctorSpecialisationEntity(
            id = speciality.id,
            code = speciality.specialtyCode,
            name = speciality.specialtyName
        )
    }

fun DoctorData.doctorSpecialityCrossReferences() =
    specialities.map { speciality ->
        DoctorSpecialisationCrossRef(
            doctorId = id,
            specialisationId = speciality.id
        )
    }


