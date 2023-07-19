package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.renderer

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.aya.digital.core.ext.drawables
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.databinding.ItemDoctorsMarkerBinding
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.databinding.ItemDoctorsMarkerClusterBinding
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorMarkerModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import timber.log.Timber

internal class DoctorMapMarkerRenderer(
    val context: Context,
    layoutInflater: LayoutInflater,
    val map: GoogleMap,
    clusterManager: ClusterManager<DoctorMarkerModel>
): DefaultClusterRenderer<DoctorMarkerModel>(
    context,
    map,
    clusterManager
) {
    private val mIconGenerator = IconGenerator(context)
    private val mClusterIconGenerator = IconGenerator(context)
    private var mClusterCountTv: TextView? = null
    private var doctorAvatarIv:ImageView? = null

    init {
        val clusterBinding = ItemDoctorsMarkerClusterBinding.inflate(layoutInflater)
        mClusterCountTv = clusterBinding.tvClusterSize
        mClusterIconGenerator.setContentView(clusterBinding.root)
        val doctorBinding = ItemDoctorsMarkerBinding.inflate(layoutInflater)
        doctorAvatarIv = doctorBinding.doctorAvatarIv
        mIconGenerator.setContentView(doctorBinding.root)
        mIconGenerator.setBackground(context.drawables[android.R.color.transparent])
        mClusterIconGenerator.setBackground(context.drawables[android.R.color.transparent])
    }

    override fun onBeforeClusterRendered(
        cluster: Cluster<DoctorMarkerModel?>,
        markerOptions: MarkerOptions
    ) {
        // Draw multiple people.
        // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
        mClusterCountTv?.text = "${cluster.size}"
        markerOptions.icon(getClusterIcon(cluster))
    }


    override fun onBeforeClusterItemRendered(
        item: DoctorMarkerModel,
        markerOptions: MarkerOptions
    ) {
        showDoctorImage(item.doctorAvatar)
        markerOptions.icon(getItemIcon(item))
    }

    override fun onClusterItemUpdated(
        item: DoctorMarkerModel,
        marker: Marker
    ) {
        showDoctorImage(item.doctorAvatar)
        marker.setIcon(getItemIcon(item))
    }

    private fun getItemIcon(item: DoctorMarkerModel): BitmapDescriptor? {
        val icon = mIconGenerator.makeIcon()
        return BitmapDescriptorFactory.fromBitmap(icon)
    }


    override fun onClusterUpdated(
        cluster: Cluster<DoctorMarkerModel?>,
        marker: Marker
    ) {
        // Same implementation as onBeforeClusterRendered() (to update cached markers)
        mClusterCountTv?.text = "${cluster.size}"
        marker.setIcon(getClusterIcon(cluster))
    }

    private fun getClusterIcon(cluster: Cluster<DoctorMarkerModel?>): BitmapDescriptor {
        val icon = mClusterIconGenerator.makeIcon(cluster.size.toString())
        return BitmapDescriptorFactory.fromBitmap(icon)
    }


    override fun shouldRenderAsCluster(cluster: Cluster<DoctorMarkerModel>): Boolean {
        return cluster.size > 1
    }

    private fun showDoctorImage(photo:String?)
    {
        Timber.d("$photo $doctorAvatarIv")
        if(doctorAvatarIv==null || photo==null) return
        Glide
            .with(doctorAvatarIv!!)
            .load(photo)
            .transform(
                CircleCrop()
            )
            .dontAnimate()
            .into(doctorAvatarIv!!)
    }


}