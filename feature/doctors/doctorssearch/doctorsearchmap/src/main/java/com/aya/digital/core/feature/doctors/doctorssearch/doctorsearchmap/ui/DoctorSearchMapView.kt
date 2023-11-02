package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.databinding.ViewDoctorsearchMapBinding
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.di.doctorSearchMapDiModule
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorMarkerModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorSearchMapStateTransformer
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorSearchMapUiModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.renderer.DoctorMapMarkerRenderer
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapSideEffects
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapState
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.google.maps.android.clustering.ClusterManager
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber


internal class DoctorSearchMapView :
    DiFragment<ViewDoctorsearchMapBinding, DoctorSearchMapViewModel, DoctorSearchMapState, DoctorSearchMapSideEffects, DoctorSearchMapUiModel, DoctorSearchMapStateTransformer>() {

    private val viewModelFactory: ((Unit) -> DoctorSearchMapViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorSearchMapStateTransformer) by kodein.on(
        context = this
    ).factory()

    private var clusterManager: ClusterManager<DoctorMarkerModel>? = null
    private var map: GoogleMap? = null

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                DoctorItemDelegate(viewModel::onDoctorClicked, viewModel::onDoctorFavouriteClicked)
            }
        }
    }

    private val clusterManagerReadySubject = BehaviorSubject.create<Boolean>().apply { onNext(false) }
    private var mMap: MapView? = null

    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    @SuppressLint("MissingPermission")
    private val requestLocationPermissionsResult = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                viewModel.getLocation()
                map?.isMyLocationEnabled = true

            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                viewModel.getLocation()
                map?.isMyLocationEnabled = true

            }

            else -> {
                Toast.makeText(
                    requireContext(),
                    "Please allow Location in App Settings to get doctors for your location",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun requestLocationPermissions() {
        var displayRational = false
        for (permission in locationPermissions) {
            displayRational =
                displayRational or ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    permission
                )
        }
        if (displayRational) {
            Toast.makeText(
                requireContext(),
                "Please allow Location in App Settings to get doctors for your location",
                Toast.LENGTH_LONG
            ).show()
        } else {

            requestLocationPermissionsResult.launch(
                locationPermissions
            )
        }
    }

    private fun checkPermissionForLocation(): Boolean {
        return checkPermissions(
            locationPermissions
        )
    }

    private fun checkPermissions(permissions: Array<String>): Boolean {
        var shouldCheck = true
        for (permission in permissions) {
            shouldCheck = shouldCheck and (PackageManager.PERMISSION_GRANTED ==
                    ContextCompat.checkSelfPermission(requireActivity(), permission))
        }
        return shouldCheck
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        getGeolocation()
        clusterManagerReadySubject.onNext(false)
        mMap = binding.mapView
        mMap?.onCreate(savedInstanceState)
        mMap?.getMapAsync {
            if (map != null && savedInstanceState!=null) return@getMapAsync
            map = it
            configureMap()
            configureCluster()
            map?.setOnMapClickListener(viewModel::onMapClick)
        }
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(3)
            isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                true
            )
            layoutManager = lm
        }
    }

    @SuppressLint("MissingPermission")
    private fun getGeolocation() {
        if (!checkPermissionForLocation()) {
            requestLocationPermissions()
        } else {
            map?.isMyLocationEnabled = true
            viewModel.getLocation()
        }
    }

    private fun configureMap() {

        with(map?.uiSettings)
        {
            this?.isZoomControlsEnabled = true
            this?.isMyLocationButtonEnabled = true
            this?.isMapToolbarEnabled = true
            this?.isCompassEnabled = true
        }


    }

    @SuppressLint("PotentialBehaviorOverride")
    private fun configureCluster() {
        clusterManager = ClusterManager(requireActivity(), map)
        clusterManager?.renderer =
            DoctorMapMarkerRenderer(requireActivity(), layoutInflater, map!!, clusterManager!!)
        map?.setOnCameraIdleListener(clusterManager)
        map?.setOnMarkerClickListener(clusterManager)
        map?.setOnInfoWindowClickListener(clusterManager)

        clusterManager?.setOnClusterClickListener { cluster ->
            val doctorMarkerModels = cluster?.items
            if (doctorMarkerModels.isNullOrEmpty()) return@setOnClusterClickListener true
            if (calculateClusterDensityIsSingleAddress(doctorMarkerModels.toList())) {
                val doctorIds = doctorMarkerModels.map { it.doctorId }
                viewModel.onDoctorsClusterClicked(doctorIds)
            } else {
                map?.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        cluster.position,
                        map?.cameraPosition!!.zoom + ZOOM_FACTOR
                    )
                )
            }
            return@setOnClusterClickListener true
        }
        clusterManager?.setOnClusterItemClickListener {
            viewModel.onDoctorMarkerClicked(it.doctorId)
            true
        }
        clusterManagerReadySubject.onNext(true)
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
    }


    override fun provideDiModule(): DI.Module = doctorSearchMapDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchMapBinding = ViewDoctorsearchMapBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: DoctorSearchMapSideEffects) =
        when (sideEffect) {
            is DoctorSearchMapSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is DoctorSearchMapSideEffects.OnLocationChanged -> {
                moveMap(sideEffect.location)
            }
        }

    private fun moveMap(location: Location) {
        map?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude),
                map?.cameraPosition!!.zoom + ZOOM_FACTOR
            )
        )
    }

    @SuppressLint("CheckResult")
    override fun render(state: DoctorSearchMapState) {
        stateTransformer(state).run {
            data?.let { list ->
                binding.recycler.toggleVisibility(list.isNotEmpty())
                adapter.items = list
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            markers?.let { list ->
                if (list.isEmpty()) return@let
                clusterManagerReadySubject.subscribe {
                    if(!it) return@subscribe
                    clusterManager?.clearItems()
                    clusterManager?.addItems(list)
                    clusterManager?.cluster()
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        mMap?.onResume()
    }


    override fun onPause() {
        super.onPause()
        mMap?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMap?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMap?.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clusterManager = null
    }

    override fun provideViewModel(): DoctorSearchMapViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchMapStateTransformer =
        stateTransformerFactory(Unit)

    private fun calculateClusterDensityIsSingleAddress(doctorMarkerModels: List<DoctorMarkerModel>): Boolean {
        var maxDistance = 0.0
        val firstPoint = doctorMarkerModels.first().position
        for (i in 1 until doctorMarkerModels.size) {
            val point = doctorMarkerModels[i].position
            val distance = SphericalUtil.computeDistanceBetween(firstPoint, point)
            if (distance > maxDistance) maxDistance = distance
        }
        return maxDistance < CLUSTER_MAX_DISTANCE
    }

    companion object {
        private const val ZOOM_FACTOR = 4.5f
        private const val DEFAULT_ZOOM = 10f
        private const val CLUSTER_MAX_DISTANCE = 50.0
    }
}
