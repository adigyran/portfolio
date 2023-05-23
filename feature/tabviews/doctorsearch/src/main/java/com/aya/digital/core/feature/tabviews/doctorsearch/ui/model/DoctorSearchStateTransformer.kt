package com.aya.digital.core.feature.tabviews.doctorsearch.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctors.doctoritem.model.DoctorItemUIModel

class DoctorSearchStateTransformer(context: Context) :
    BaseStateTransformer<DoctorSearchState, DoctorSearchUiModel>() {
    override fun invoke(state: DoctorSearchState): DoctorSearchUiModel =
        DoctorSearchUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.doctors?.run {
                        addAll(map { doctor ->
                            DoctorItemUIModel(
                                id = doctor.id,
                                name = doctor.composeName(),
                                speciality = doctor.getSpeciality(),
                                photo = doctor.avatarPhotoLink
                            )
                        })
                    }
                }
            }
        )

    private fun com.aya.digital.core.domain.base.models.doctors.DoctorModel.composeName() = "Dr. %s, %s".format(lastName,clinics.firstOrNull()?.clinicName)
    private fun com.aya.digital.core.domain.base.models.doctors.DoctorModel.getSpeciality() = "%s".format(specialities.firstOrNull()?.name)

}