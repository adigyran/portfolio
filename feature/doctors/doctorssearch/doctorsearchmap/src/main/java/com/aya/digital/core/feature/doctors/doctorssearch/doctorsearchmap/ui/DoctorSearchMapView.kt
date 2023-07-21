package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.aya.digital.core.ui.base.utils.EndlessRecyclerViewScrollListener
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.SphericalUtil
import com.google.maps.android.clustering.ClusterManager
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

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync {
            if (map != null) return@getMapAsync
            map = it
            configureMap()
            configureCluster()
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

    private fun configureMap() {
        with(map?.uiSettings)
        {
            this?.isZoomControlsEnabled = true
            this?.isMyLocationButtonEnabled = true
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
            Timber.d("$cluster")
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
            Timber.d("$it")
            viewModel.onDoctorMarkerClicked(it.doctorId)
            true
        }
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
        }

    override fun render(state: DoctorSearchMapState) {
        stateTransformer(state).run {
            data?.let {list->
                binding.recycler.toggleVisibility(list.isNotEmpty())
                adapter.items = list
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            markers?.let { list ->
                if (list.isEmpty()) return@run
              /*  map?.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        list.first().position,
                        map?.cameraPosition!!.zoom + ZOOM_FACTOR
                    )
                )*/
                Timber.d("RENDER $clusterManager ${list.toString()}")
                clusterManager?.clearItems()
                clusterManager?.addItems(list)
                clusterManager?.cluster()
            }
        }
        /*stateTransformer(state).data?.let {

        }*/
    }

    override fun onResume() {
        binding.mapView.onResume()
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
       // binding.mapView.onDestroy()
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
