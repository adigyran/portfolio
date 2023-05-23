/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aya.digital.core.database.model.doctor

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.aya.digital.core.data.doctors.DoctorData

/**
 * Defines an NiA news resource.
 */
data class DoctorsResource(
    @Embedded
    val entity: DoctorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = DoctorSpecialisationCrossRef::class,
            parentColumn = "doctor_id",
            entityColumn = "specialisation_id",
        )
    )
    val specialisations: List<DoctorSpecialisationEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = DoctorClinicCrossRef::class,
            parentColumn = "doctor_id",
            entityColumn = "clinic_id",
        )
    )
    val clinics: List<DoctorClinicEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = DoctorInsuranceCrossRef::class,
            parentColumn = "doctor_id",
            entityColumn = "insurance_id",
        )
    )
    val insurances: List<InsuranceEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "doctorId"
    )
    val location: DoctorLocationEntity


)

fun DoctorsResource.asExternalModel() = DoctorData(
    id = entity.id,
    firstName = entity.firstName,
    lastName = entity.lastName,
    middleName = entity.middleName,
    avatarPhotoLink = entity.avatarPhotoLink,
    bio = entity.bio,
    city = entity.city,
    address = entity.address,
    postalCode = entity.postalCode,
    specialities = specialisations.map(DoctorSpecialisationEntity::asExternalModel),
    insurances = insurances.map(InsuranceEntity::asExternalModel),
    clinics = clinics.map(DoctorClinicEntity::asExternalModel),
    location = location.asExternalModel()
)