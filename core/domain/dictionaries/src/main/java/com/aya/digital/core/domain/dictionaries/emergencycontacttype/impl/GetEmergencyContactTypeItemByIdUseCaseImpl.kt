package com.aya.digital.core.domain.dictionaries.emergencycontacttype.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.dictionaries.EmergencyContactTypeModel
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.GetEmergencyContactTypeItemByIdUseCase
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItem
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItemPaginationModel
import com.aya.digital.core.domain.dictionaries.insurancecompany.model.InsuranceCompanyItem
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetEmergencyContactTypeItemByIdUseCaseImpl(
    private val dictionariesRepository: DictionariesRepository,
    private val progressRepository: ProgressRepository
) :
    GetEmergencyContactTypeItemByIdUseCase {
    override fun invoke(typeId: Int): Single<RequestResultModel<EmergencyContactTypeItem>> =
        dictionariesRepository.getEmergencyContactTypeById(typeId)
            .trackProgress(progressRepository)
            .mapResult({ EmergencyContactTypeItem(it.id, it.name ?: "").asResultModel() },
                { it.toModelError() })
}