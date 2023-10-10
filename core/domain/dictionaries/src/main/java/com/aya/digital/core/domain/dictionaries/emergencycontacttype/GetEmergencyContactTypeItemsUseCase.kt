package com.aya.digital.core.domain.dictionaries.emergencycontacttype

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItemPaginationModel
import io.reactivex.rxjava3.core.Flowable

fun interface GetEmergencyContactTypeItemsUseCase {
    operator fun invoke(
        searchTerm: String?,
        selectedItems:Set<Int>,
        cursor: String?
    ): Flowable<RequestResultModel<EmergencyContactTypeItemPaginationModel>>
}