package com.aya.digital.core.feature.tabviews.doctorsearch.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchState
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctors.doctoritem.model.DoctorItemUIModel
import java.lang.StringBuilder

class DoctorSearchStateTransformer(private val context: Context) :
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
            },
            specialityFilterText = kotlin.run {
                if(state.selectedFilters.filterIsInstance<SelectedFilterModel.Speciality>().isEmpty()) return@run "Speciality"
                var prefix = ""
                return@run state.selectedFilters.filterIsInstance<SelectedFilterModel.Speciality>()
                    .fold(StringBuilder()) { acc, speciality ->
                        acc.append(prefix)
                        prefix = ", "
                        acc.append(
                            speciality.name
                        )

                    }.toString()
            },
            locationFilterText = kotlin.run {
                if(state.selectedFilters.filterIsInstance<SelectedFilterModel.Location>().isEmpty()) return@run "Location"
                var prefix = ""
                return@run state.selectedFilters.filterIsInstance<SelectedFilterModel.Location>()
                    .fold(StringBuilder()) { acc, location ->
                        acc.append(prefix)
                        prefix = ", "
                        acc.append(
                            location.name
                        )
                    }.toString()
            },
            insuranceFilterText = kotlin.run {
                if(state.selectedFilters.filterIsInstance<SelectedFilterModel.Insurance>().isEmpty()) return@run "Insurance"
                var prefix = ""
                return@run state.selectedFilters.filterIsInstance<SelectedFilterModel.Insurance>()
                    .fold(StringBuilder()) { acc, insurance ->
                        acc.append(prefix)
                        prefix = ", "
                        acc.append(
                            insurance.name
                        )
                    }.toString()
            },
        )
    private fun DoctorModel.composeName() =
        "Dr. %s, %s".format(lastName, clinics.firstOrNull()?.clinicName)

    private fun DoctorModel.getSpeciality() = "%s".format(specialities.firstOrNull()?.name)

}