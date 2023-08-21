package com.aya.digital.core.domain.profile.insurance.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.profile.repository.PractitionerInsuranceRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.profile.insurance.UpdateDoctorInsurancesUseCase
import io.reactivex.rxjava3.core.Observable

internal class UpdateDoctorInsurancesUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val practitionerInsuranceRepository: PractitionerInsuranceRepository
) : UpdateDoctorInsurancesUseCase {
    override fun invoke(ids: List<Int>): Observable<RequestResultModel<List<Int>>> {
        TODO("Not yet implemented")
    }
}