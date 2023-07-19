package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model

import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

internal data class DoctorMarkerModel(
    private val lat: Double,
    private val lon: Double,
    val doctorModel: DoctorModel,
    private val name: String
) : ClusterItem {
    override fun getPosition(): LatLng = LatLng(lat, lon)

    override fun getTitle(): String = name

    override fun getSnippet(): String? = null
}