package com.aya.digital.core.feature.profile.address.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.core.feature.profile.address.databinding.ViewProfileAddressBinding
import com.aya.digital.core.feature.profile.address.di.profileAddressDiModule
import com.aya.digital.core.feature.profile.address.ui.RxSearchObservable.fromView
import com.aya.digital.core.feature.profile.address.ui.model.ProfileAddressStateTransformer
import com.aya.digital.core.feature.profile.address.ui.model.ProfileAddressUiModel
import com.aya.digital.core.feature.profile.address.viewmodel.ProfileAddressSideEffects
import com.aya.digital.core.feature.profile.address.viewmodel.ProfileAddressState
import com.aya.digital.core.feature.profile.address.viewmodel.ProfileAddressViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.insurance.ui.AutocompleteAddressSuggestionDelegate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber
import java.util.Locale
import java.util.concurrent.TimeUnit


class ProfileAddressView :
    DiFragment<ViewProfileAddressBinding, ProfileAddressViewModel, ProfileAddressState, ProfileAddressSideEffects, ProfileAddressUiModel, ProfileAddressStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileAddressViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileAddressStateTransformer) by kodein.on(
        context = this
    ).factory()

    private var map: GoogleMap? = null
    private var mMap: MapView? = null
    private var marker: Marker? = null

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                AutocompleteAddressSuggestionDelegate(viewModel::onAddressSuggestionClicked)
            }
        }
    }


    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.toolbar.title.text = "Address"
        binding.saveBtn bindClick {viewModel.onSaveClick()}
        mMap = binding.mapView
        mMap?.onCreate(savedInstanceState)
        mMap?.getMapAsync {
            if (map != null && savedInstanceState!=null) return@getMapAsync
            map = it
            configureMap()
            map?.setOnMapClickListener(viewModel::onMapClick)
          //  configureCluster()
        }


        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(3)
            isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            layoutManager = lm
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

        initSearchView(binding.searchView)

    }

    private fun initSearchView(searchView: SearchView) {
        searchView.queryHint = "search an address"
        searchView.isFocusable = true
        searchView.isIconified = false
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Timber.d(query)
                viewModel.querySearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })
    }

    override fun provideDiModule(): DI.Module = profileAddressDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileAddressBinding =
        ViewProfileAddressBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileAddressSideEffects) = when(sideEffect)
    {
        is ProfileAddressSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        is ProfileAddressSideEffects.OnLocationChanged -> {moveMap(LatLng(sideEffect.location.latitude,sideEffect.location.longitude))}
        is ProfileAddressSideEffects.MoveMap -> {moveMap(sideEffect.latLng)}
    }


    private fun moveMap(location:LatLng) {
        map?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                location,
                map?.cameraPosition!!.zoom + ZOOM_FACTOR
            )
        )
    }
    override fun render(state: ProfileAddressState) {
        stateTransformer(state).run {
            data?.let { list ->
                adapter.items = list
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            selectedAddressLoc?.let {position->
                marker?.remove()
                marker =  map?.addMarker(
                    MarkerOptions()
                    .position(position))
                  //  .snippet(getString(R.string.default_info_snippet)))
            }
            binding.currentAddress.text = currentAddress?:" "

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


    override fun provideViewModel(): ProfileAddressViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileAddressStateTransformer =
        stateTransformerFactory(Unit)

    companion object {
        private const val ZOOM_FACTOR = 15f
        private const val DEFAULT_ZOOM = 10f
        private const val CLUSTER_MAX_DISTANCE = 50.0
    }

}
