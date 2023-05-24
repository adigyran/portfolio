package com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class SelectedFilterModel:Parcelable {
    data class Speciality(val id:Int, val name:String) : SelectedFilterModel()
    data class Insurance(val id:Int, val name: String) : SelectedFilterModel()
    data class Location(val id:Int,val name: String):SelectedFilterModel()
}
