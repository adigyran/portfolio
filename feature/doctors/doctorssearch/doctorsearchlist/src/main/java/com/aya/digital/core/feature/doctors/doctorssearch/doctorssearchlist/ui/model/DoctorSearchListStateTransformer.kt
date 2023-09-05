package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.DoctorSearchListMode
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel.DoctorSearchListState
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctors.doctoritem.model.DoctorItemUIModel
import java.lang.StringBuilder

class DoctorSearchListStateTransformer(private val context: Context) :
    BaseStateTransformer<DoctorSearchListState, DoctorSearchListUiModel>() {

    override fun invoke(state: DoctorSearchListState): DoctorSearchListUiModel =
        DoctorSearchListUiModel(
            doctorSearchMode = state.doctorSearchMode,
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    when (state.doctorSearchMode) {
                        DoctorSearchListMode.ShowingAllDoctors -> {
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

                        DoctorSearchListMode.ShowingFavoriteDoctors -> {
                            state.doctors?.run {
                                addAll(
                                    filter { doctorModel -> checkIsFavorite(doctorModel.id, state) }
                                        .map { doctor ->
                                            DoctorItemUIModel(
                                                id = doctor.id,
                                                name = doctor.composeName(),
                                                speciality = doctor.getSpeciality(),
                                                photo = doctor.avatarPhotoLink,
                                                isFavorite = true
                                            )
                                        })
                            }
                        }
                    }
                }
            },
            specialityFilterText = kotlin.run {
                if (state.selectedFilters.filterIsInstance<SelectedFilterModel.Speciality>()
                        .isEmpty()
                ) return@run "Speciality"
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
                if (state.selectedFilters.filterIsInstance<SelectedFilterModel.Location>()
                        .isEmpty()
                ) return@run "Location"
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
                if (state.selectedFilters.filterIsInstance<SelectedFilterModel.Insurance>()
                        .isEmpty()
                ) return@run "Insurance"
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
            filtersEnabled = state.selectedFilters.isNotEmpty()

        )

    private fun checkIsFavorite(id: Int, state: DoctorSearchListState): Boolean = state.favoriteDoctors?.contains(id)?:false

    private fun DoctorModel.composeName() =
        "Dr. %s, %s".format(lastName, clinics.firstOrNull()?.clinicName)

    private fun DoctorModel.getSpeciality() = "%s".format(specialities.firstOrNull()?.name?:"")

}