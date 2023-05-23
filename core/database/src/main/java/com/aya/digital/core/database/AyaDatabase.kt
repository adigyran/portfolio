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

package com.aya.digital.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aya.digital.core.database.dao.DoctorsDao
import com.aya.digital.core.database.model.doctor.DoctorClinicCrossRef
import com.aya.digital.core.database.model.doctor.DoctorClinicEntity
import com.aya.digital.core.database.model.doctor.DoctorEntity
import com.aya.digital.core.database.model.doctor.DoctorInsuranceCrossRef
import com.aya.digital.core.database.model.doctor.DoctorLocationEntity
import com.aya.digital.core.database.model.doctor.DoctorSpecialisationCrossRef
import com.aya.digital.core.database.model.doctor.DoctorSpecialisationEntity
import com.aya.digital.core.database.model.doctor.InsuranceEntity

@Database(
    entities = [
        DoctorClinicEntity::class,
        DoctorSpecialisationEntity::class,
        DoctorEntity::class,
        DoctorClinicCrossRef::class,
        DoctorSpecialisationCrossRef::class,
        InsuranceEntity::class,
        DoctorInsuranceCrossRef::class,
        DoctorLocationEntity::class
    ],
    version = 2,
    autoMigrations = [

    ],
    exportSchema = false,
)
@TypeConverters(

)
abstract class AyaDatabase : RoomDatabase() {
    abstract fun doctorsDao(): DoctorsDao
}
