package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import java.lang.StringBuilder

class DoctorSearchContainerStateTransformer(private val context: Context) :
    BaseStateTransformer<DoctorSearchContainerState, DoctorSearchContainerUiModel>() {

    override fun invoke(state: DoctorSearchContainerState): DoctorSearchContainerUiModel =
        DoctorSearchContainerUiModel(

        )


}