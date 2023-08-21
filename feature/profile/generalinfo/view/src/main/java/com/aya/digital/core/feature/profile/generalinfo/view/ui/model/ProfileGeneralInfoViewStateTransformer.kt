package com.aya.digital.core.feature.profile.generalinfo.view.ui.model

import android.content.Context
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewState
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.model.getSexName
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.base.masks.CommonMasks
import com.aya.digital.core.ui.delegates.profile.info.model.ProfileInfoUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import ru.tinkoff.decoro.MaskImpl

class ProfileGeneralInfoViewStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils,
    private val appFlavour: AppFlavour
) :
    BaseStateTransformer<ProfileGeneralInfoViewState, ProfileGeneralInfoViewUiModel>() {
    override fun invoke(state: ProfileGeneralInfoViewState): ProfileGeneralInfoViewUiModel =
        ProfileGeneralInfoViewUiModel(
            data = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    add(ProfileInfoUIModel("Email", state.email.getField()))
                    add(ProfileInfoUIModel("First Name", state.firstName.getField()))
                    add(ProfileInfoUIModel("Last Name", state.lastName.getField()))
                    add(ProfileInfoUIModel("Middle Name", state.middleName.getField()))
                    add(
                        ProfileInfoUIModel(
                            "Date of Birth",
                            state.dateOfBirth?.toKotlinLocalDate().getField()
                        )
                    )
                    addAll(appFlavour.flavour.getAppSpecificFields(state))

                }
            },
            avatar = state.avatar
        )

    private fun Flavor.getAppSpecificFields(state: ProfileGeneralInfoViewState) = when (this) {
        Flavor.Doctor -> getDoctorFields(state)
        Flavor.Patient -> getPatientFields(state)
    }

    private fun getPatientFields(state: ProfileGeneralInfoViewState) =
        mutableListOf<DiffItem>().apply {
            add(
                ProfileInfoUIModel(
                    "SSN",
                    state.patientFields?.ssn.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )
            add(
                ProfileInfoUIModel(
                    "TIN",
                    state.patientFields?.tin.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )

            add(ProfileInfoUIModel("Sex", state.patientFields?.sex.getField()))
            add(
                ProfileInfoUIModel(
                    "Height",
                    state.patientFields?.height.getMaskedText(CommonMasks.getHeightValidator())
                        .getUnitField(getHeightUnit())
                )
            )
            add(
                ProfileInfoUIModel(
                    "Weight",
                    state.patientFields?.weight.getMaskedText(CommonMasks.getWeightValidator())
                        .getUnitField(getWeightUnit())
                )
            )
            add(ProfileInfoUIModel("Short Address", state.patientFields?.shortAddress.getField()))
        }

    private fun getDoctorFields(state: ProfileGeneralInfoViewState) =
        mutableListOf<DiffItem>().apply {
            add(
                ProfileInfoUIModel(
                    "NPI",
                    state.doctorFields?.npi.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )
            add(
                ProfileInfoUIModel(
                    "TIN",
                    state.doctorFields?.tin.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )
            add(
                ProfileInfoUIModel(
                    "License number",
                    state.doctorFields?.licenseNumber.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )
            add(
                ProfileInfoUIModel(
                    "Language",
                    state.doctorFields?.language.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )
            add(
                ProfileInfoUIModel(
                    "Bio",
                    state.doctorFields?.bio.getMaskedText(CommonMasks.getSSNValidator()).getField()
                )
            )
        }

    private fun getNotSpecifiedText() = "Not specified"
    private fun String?.getField() = this ?: getNotSpecifiedText()
    private fun ProfileSex?.getField() = this?.sexName() ?: getNotSpecifiedText()
    private fun LocalDate?.getField() =
        this?.let { dateTimeUtils.formatBirthDate(it) } ?: getNotSpecifiedText()

    private fun ProfileSex.sexName() = this.getSexName(context)

    private fun String?.getUnitField(unit: String) =
        this?.let { "%s %s".format(it, unit) } ?: getNotSpecifiedText()

    private fun String?.getMaskedText(mask: MaskImpl) = this?.let {
        mask.insertFront(it)
        mask.toString()
    }

    private fun getHeightUnit() = "ft."
    private fun getWeightUnit() = "lbs"
}

