package com.aya.digital.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aya.digital.core.database.model.doctor.DoctorClinicCrossRef
import com.aya.digital.core.database.model.doctor.DoctorClinicEntity
import com.aya.digital.core.database.model.doctor.DoctorEntity
import com.aya.digital.core.database.model.doctor.DoctorInsuranceCrossRef
import com.aya.digital.core.database.model.doctor.DoctorLocationEntity
import com.aya.digital.core.database.model.doctor.DoctorSpecialisationCrossRef
import com.aya.digital.core.database.model.doctor.DoctorSpecialisationEntity
import com.aya.digital.core.database.model.doctor.DoctorsResource
import com.aya.digital.core.database.model.doctor.InsuranceEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [TopicEntity] access
 */
@Dao
interface DoctorsDao {

    @Query("SELECT EXISTS(SELECT * FROM doctors WHERE id = :id)")
    fun isDoctorExist(id : Int) : Single<Boolean>

    @Transaction
    @Query(
        value = """
            SELECT * FROM doctors 
            WHERE id = :doctorId
    """
    )

    fun getDoctor(
        doctorId: Int
    ): Single<DoctorsResource>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreInsurances(insuranceEntities: List<InsuranceEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreSpecialisations(specialisationEntities: List<DoctorSpecialisationEntity>): Single<List<Long>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreClinics(clinicEntities: List<DoctorClinicEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreDoctor(entity: DoctorEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreLocation(entity: DoctorLocationEntity?): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreDoctorInsuranceCrossRefEntities(
        doctorInsuranceCrossReferences: List<DoctorInsuranceCrossRef>
    ):Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreDoctorSpecialisationCrossRefEntities(
        doctorSpecialisationCrossReferences: List<DoctorSpecialisationCrossRef>
    ):Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreDoctorClinicCrossRefEntities(
        doctorClinicCrossReferences: List<DoctorClinicCrossRef>
    ):Completable

    @Query(
        value = """
            DELETE FROM doctors
            WHERE id in (:ids)
        """
    )
    fun deleteDoctors(ids: List<Int>): Completable
}