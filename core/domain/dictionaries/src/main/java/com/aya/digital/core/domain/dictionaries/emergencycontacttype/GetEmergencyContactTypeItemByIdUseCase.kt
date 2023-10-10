package com.aya.digital.core.domain.dictionaries.emergencycontacttype

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.dictionaries.EmergencyContactTypeModel
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItem
import com.aya.digital.core.domain.dictionaries.emergencycontacttype.model.EmergencyContactTypeItemPaginationModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

fun interface GetEmergencyContactTypeItemByIdUseCase {
    operator fun invoke(
        typeId:Int
    ): Single<RequestResultModel<EmergencyContactTypeItem>>
}