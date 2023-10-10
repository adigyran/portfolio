package com.aya.digital.core.domain.profile.emergencycontact.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmergencyContactModel(val Id:Int,val name:String?,val phone:String?,val summary:String?,val type: EmergencyContactTypeModel?):Parcelable


@Parcelize
data class EmergencyContactTypeModel(val id:Int, val name: String?):Parcelable