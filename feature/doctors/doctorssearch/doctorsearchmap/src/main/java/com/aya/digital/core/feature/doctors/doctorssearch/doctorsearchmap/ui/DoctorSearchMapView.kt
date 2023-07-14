package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.toggleSelection
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.databinding.ViewDoctorsearchMapBinding
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.di.doctorSearchMapDiModule
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorSearchMapStateTransformer
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.model.DoctorSearchMapUiModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapSideEffects
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapState
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.viewmodel.DoctorSearchMapViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.EndlessRecyclerViewScrollListener
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorSearchMapView :
    DiFragment<ViewDoctorsearchMapBinding, DoctorSearchMapViewModel, DoctorSearchMapState, DoctorSearchMapSideEffects, DoctorSearchMapUiModel, DoctorSearchMapStateTransformer>() {

    private val viewModelFactory: ((Unit) -> DoctorSearchMapViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorSearchMapStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                DoctorItemDelegate(viewModel::onDoctorClicked, viewModel::onDoctorFavouriteClicked)
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.toolbar.btnFindDoctor bindClick { viewModel.findDoctorClicked() }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onRefreshDoctors()
            binding.swipeRefresh.isRefreshing = false
        }
        binding.toolbar.insurance bindClick { viewModel.onInsurance() }
        binding.toolbar.speciality bindClick { viewModel.onSpecialisation() }
        binding.toolbar.location bindClick { viewModel.onLocation() }
        recyclers.add(binding.recycler)
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            layoutManager = lm
            addItemDecoration(DoctorSearchMapTabDecoration())
            val scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
                override fun onLoadMore() {
                    viewModel.loadNextPage()
                }
            }
            addOnScrollListener(scrollListener)
        }
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
                adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            }
            specialityFilterText?.let {
                binding.toolbar.speciality.binding.fieldText.text = it
            }
            locationFilterText?.let {
                binding.toolbar.location.binding.fieldText.text = it
            }
            insuranceFilterText?.let {
                binding.toolbar.insurance.binding.fieldText.text = it
            }
        }
        /*stateTransformer(state).data?.let {

        }*/
    }

    override fun provideViewModel(): DoctorSearchMapViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchMapStateTransformer =
        stateTransformerFactory(Unit)

}
