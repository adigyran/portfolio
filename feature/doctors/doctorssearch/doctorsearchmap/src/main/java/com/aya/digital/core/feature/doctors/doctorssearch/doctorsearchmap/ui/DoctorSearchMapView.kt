package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.maps.android.clustering.ClusterManager
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


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
        }
    }

    private fun configureMap() {
        clusterManager = ClusterManager(requireContext(),map)
        clusterManager?.renderer = DoctorMapMarkerRenderer(requireContext(),layoutInflater,map!!,clusterManager!!)
        map?.setOnCameraIdleListener(clusterManager)
        map?.setOnMarkerClickListener(clusterManager)
        map?.setOnInfoWindowClickListener(clusterManager)

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
            data?.let {
                clusterManager?.clearItems()
                clusterManager?.addItems(it)
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

    override fun provideViewModel(): DoctorSearchMapViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchMapStateTransformer =
        stateTransformerFactory(Unit)

}
