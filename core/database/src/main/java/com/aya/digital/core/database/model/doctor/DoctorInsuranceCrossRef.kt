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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "doctor_insurances",
    primaryKeys = ["insurance_id", "doctor_id"],
    foreignKeys = [
        ForeignKey(
            entity = InsuranceEntity::class,
            parentColumns = ["id"],
            childColumns = ["insurance_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DoctorEntity::class,
            parentColumns = ["id"],
            childColumns = ["doctor_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(value = ["insurance_id"]),
        Index(value = ["doctor_id"]),
    ],
)
data class DoctorInsuranceCrossRef(
    @ColumnInfo(name = "insurance_id")
    val insuranceId:Int,
    @ColumnInfo(name = "doctor_id")
    val doctorId: Int,
)
