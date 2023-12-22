package com.aya.digital.core.feature.tabviews.profile.ui.model

import android.content.Context
import com.aya.digital.core.baseresources.R
import com.aya.digital.core.feature.tabviews.profile.FieldsTags
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileMainUIModel
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class ProfileStateTransformer(private val context: Context, private val appFlavour: AppFlavour) :
    BaseStateTransformer<ProfileState, ProfileUiModel>() {
    override fun invoke(state: ProfileState): ProfileUiModel =
        ProfileUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(appFlavour.flavour.getAppSpecificFields())
                    add(
                        ProfileMainUIModel(
                            FieldsTags.ADDRESS_BUTTON_TAG,
                            R.drawable.ic_profile_security,
                            "Address"
                        )
                    )
                    add(
                        ProfileMainUIModel(
                            FieldsTags.SECURITY_BUTTON_TAG,
                            R.drawable.ic_profile_security,
                            "Security"
                        )
                    )
                    add(
                        ProfileMainUIModel(
                            FieldsTags.PRESCRIPTIONS_BUTTON_TAG,
                            R.drawable.ic_profile_insurance,
                            "Prescriptions"
                        )
                    )
                    add(
                        ProfileMainUIModel(
                            FieldsTags.INSURANCE_BUTTON_TAG,
                            R.drawable.ic_profile_insurance,
                            "Insurance"
                        )
                    )
                    add(
                        ProfileMainUIModel(
                            FieldsTags.NOTIFICATION_BUTTON_TAG,
                            R.drawable.ic_profile_notification,
                            "Notification"
                        )
                    )
                }
            },
            avatar = state.avatar,
            subTitle = appFlavour.flavour.getAppSpecificSubtitle(state),
            name = appFlavour.flavour.getAppSpecificName(state)
        )

    private fun Flavor.getAppSpecificFields() = when (this) {
        Flavor.Doctor -> getDoctorSpecificFields()
        Flavor.Patient -> getPatientSpecificFields()
    }

    private fun Flavor.getAppSpecificSubtitle(state: ProfileState) = when (this) {
        Flavor.Doctor -> getSpeciality(state)
        Flavor.Patient -> getAge(state)
    }

    private fun Flavor.getAppSpecificName(state: ProfileState) = when (this) {
        Flavor.Doctor -> getDoctorName(state)
        Flavor.Patient -> getPatientName(state)
    }

    private fun getPatientName(state: ProfileState) = kotlin.run {
        if (state.firstName == null || state.lastName == null) return@run null
        val lastNameInitial =
            if (state.lastName.isNotBlank()) "%s.".format(state.lastName.first()) else ""
        return@run "%s %s".format(state.firstName, lastNameInitial)
    }

    private fun getDoctorName(state: ProfileState) = kotlin.run {
        "Dr. %s %s".format(state.firstName ?: "", state.lastName ?: "")
    }

    private fun getAge(state: ProfileState) = state.dateOFBirth?.let { birthday ->
        "${
            ChronoUnit.YEARS.between(
                birthday,
                LocalDate.now()
            )
        }"
    } ?: ""

    private fun getSpeciality(state: ProfileState) =
        state.doctorProfile?.doctorSpecialities?.firstOrNull()?.name ?: ""

    private fun getDoctorSpecificFields() = mutableListOf<DiffItem>().apply {
        add(
            ProfileMainUIModel(
                FieldsTags.GENERAL_INFO_BUTTON_TAG,
                R.drawable.ic_profile_generalinfo,
                "Personal Information"
            )
        )
        /* add(
             ProfileMainUIModel(
                 FieldsTags.CLINIC_INFO_BUTTON_TAG,
                 R.drawable.ic_profile_clinic_info,
                 "Clinic info"
             )
         )*/
    }

    private fun getPatientSpecificFields() = mutableListOf<DiffItem>().apply {
        add(
            ProfileMainUIModel(
                FieldsTags.GENERAL_INFO_BUTTON_TAG,
                R.drawable.ic_profile_generalinfo,
                "General Info"
            )
        )
        add(
            ProfileMainUIModel(
                FieldsTags.EMERGENCY_CONTACT_BUTTON_TAG,
                R.drawable.ic_profile_emergency,
                "Emergency Contacts"
            )
        )
    }
}