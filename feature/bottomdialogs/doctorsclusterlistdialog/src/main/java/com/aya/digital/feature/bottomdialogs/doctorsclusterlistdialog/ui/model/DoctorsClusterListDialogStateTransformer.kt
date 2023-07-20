package com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.ui.model

import android.content.Context
import android.text.format.DateUtils
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctors.doctoritem.model.DoctorItemUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils

import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel.DoctorsClusterListDialogState
import kotlinx.datetime.*

class DoctorsClusterListDialogStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<DoctorsClusterListDialogState, DoctorsClusterListDialogUiModel>() {
    override fun invoke(state: DoctorsClusterListDialogState): DoctorsClusterListDialogUiModel =
        DoctorsClusterListDialogUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.doctors?.let { slots ->
                        state.doctors?.run {
                            addAll(
                                map { doctor ->
                                    DoctorItemUIModel(
                                        id = doctor.id,
                                        name = doctor.composeName(),
                                        speciality = doctor.getSpeciality(),
                                        photo = doctor.avatarPhotoLink,
                                        isFavorite = checkIsFavorite(doctor.id, state)
                                    )
                                })
                        }
                    }
                }

            }
        )
    private fun checkIsFavorite(id: Int,state: DoctorsClusterListDialogState): Boolean = state.favoriteDoctors?.contains(id)?:false

    private fun DoctorModel.composeName() =
        "Dr. %s, %s".format(lastName, clinics.firstOrNull()?.clinicName)

    private fun DoctorModel.getSpeciality() = "%s".format(specialities.firstOrNull()?.name)

}

