package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class SelectedFilterModel(open val id: Int):Parcelable {
    data class Speciality(override val id:Int, val name:String) : SelectedFilterModel(id)
    data class Insurance(override val id:Int, val name: String) : SelectedFilterModel(id)
    data class Location(override val id:Int,val name: String): SelectedFilterModel(id)
}
