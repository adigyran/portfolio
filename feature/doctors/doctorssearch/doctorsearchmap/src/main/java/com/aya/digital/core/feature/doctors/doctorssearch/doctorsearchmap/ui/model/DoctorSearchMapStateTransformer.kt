package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapState
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.model.SelectedFilterModel
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.doctors.doctoritem.model.DoctorItemUIModel
import java.lang.StringBuilder

internal class DoctorSearchMapStateTransformer(private val context: Context) :
    BaseStateTransformer<DoctorSearchMapState, DoctorSearchMapUiModel>() {

    override fun invoke(state: DoctorSearchMapState): DoctorSearchMapUiModel =
        DoctorSearchMapUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.selectedDoctor?.run {
                        add(
                            DoctorItemUIModel(
                                id = id,
                                name = composeName(),
                                speciality = getSpeciality(),
                                photo = avatarPhotoLink,
                                isFavorite = checkIsFavorite(id, state)
                            )
                        )
                    }
                }
            },
            markers = kotlin.run {
                return@run mutableListOf<DoctorMarkerModel>().apply {

                    state.doctors?.run {
                        addAll(
                            map { doctor ->
                                DoctorMarkerModel(
                                    lat = doctor.location.lat,
                                    lon = doctor.location.long,
                                    name = doctor.composeName(),
                                    doctorId = doctor.id,
                                    doctorAvatar = doctor.avatarPhotoLink
                                )
                            })
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
            }
        )

    private fun checkIsFavorite(id: Int, state: DoctorSearchMapState): Boolean =
        state.favoriteDoctors?.contains(id) ?: false

    private fun DoctorModel.composeName() =
        "Dr. %s, %s".format(lastName, clinics.firstOrNull()?.clinicName?:"")

    private fun DoctorModel.getSpeciality() = "%s".format(specialities.firstOrNull()?.name?:"")

}