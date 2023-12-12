package com.aya.digital.core.repository.appointment

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.datasource.AppointmentDataSource
import com.aya.digital.core.datasource.PrescriptionsDataSource
import com.aya.digital.core.datasource.TeleHealthDataSource
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

internal class PrescriptionsRepositoryImpl(
    private val prescriptionsDataSource: PrescriptionsDataSource
) : PrescriptionsRepository {
    override fun subscribeToPrescriptions(
        practitionerId: Int,
        patientId: Int,
        appointmentId: Int
    ): Single<RequestResult<Boolean>> {
        TODO("Not yet implemented")
    }

}