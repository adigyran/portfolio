package com.aya.digital.core.feature.profile.emergencycontact.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.profile.emergencycontact.model.EmergencyContactModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileEmergencyContactState(
    val emergencyContacts: List<EmergencyContactModel>? = null,
    val editableEmergencyContact: EditableEmergencyContact? = null,
    val currentEditableId:Int?=null,
    val contactTypeEditable: String? = null,
    var contactTypeEditableId: Int? = null,
    val contactTypeError: String? = null,
    val editMode: Boolean = false
) : BaseState


@Parcelize
data class EditableEmergencyContact(
    var contactNameEditable: String? = null,
    var contactNameError: String? = null,
    var contactPhoneEditable: String? = null,
    var contactPhoneError: String? = null,
    var contactSummaryEditable: String? = null,
    var contactSummaryError: String? = null,


) : Parcelable
