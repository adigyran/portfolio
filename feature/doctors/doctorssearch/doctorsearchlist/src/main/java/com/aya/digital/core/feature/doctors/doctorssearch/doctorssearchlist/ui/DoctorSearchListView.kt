package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ext.toggleAvailability
import com.aya.digital.core.ext.toggleSelection
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchlist.R
import com.aya.digital.core.designsystem.R as DR
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchlist.databinding.ViewDoctorsearchListBinding
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.DoctorSearchListMode
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.di.doctorSearchListDiModule
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui.model.DoctorSearchListStateTransformer
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui.model.DoctorSearchListUiModel
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel.DoctorSearchListSideEffects
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel.DoctorSearchListState
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.viewmodel.DoctorSearchListViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.EndlessRecyclerViewScrollListener
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorSearchListView :
    DiFragment<ViewDoctorsearchListBinding, DoctorSearchListViewModel, DoctorSearchListState, DoctorSearchListSideEffects, DoctorSearchListUiModel, DoctorSearchListStateTransformer>() {

    private val viewModelFactory: ((Unit) -> DoctorSearchListViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorSearchListStateTransformer) by kodein.on(
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
        binding.toolbar.btnApplyFilters bindClick { viewModel.applyFiltersClicked() }
        binding.toolbar.btnClearFilters bindClick { viewModel.clearFiltersClicked() }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onRefreshDoctors()
            binding.swipeRefresh.isRefreshing = false
        }
        binding.toolbar.insurance bindClick { viewModel.onInsurance() }
        binding.toolbar.speciality bindClick { viewModel.onSpecialisation() }
        binding.toolbar.location bindClick { viewModel.onLocation() }
        binding.toolbar.allDoctorsBtn bindClick { viewModel.onAllDoctorsClicked() }
        binding.toolbar.favoriteButton bindClick { viewModel.onFavoriteDoctorsClicked() }
        recyclers.add(binding.recycler)
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(30)
            //isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            layoutManager = lm
            addItemDecoration(DoctorSearchListTabDecoration())
            val scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
                override fun onLoadMore() {
                    viewModel.loadNextPage()
                }
            }
            addOnScrollListener(scrollListener)
        }
    }


    override fun provideDiModule(): DI.Module = doctorSearchListDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchListBinding = ViewDoctorsearchListBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: DoctorSearchListSideEffects) =
        when (sideEffect) {
            is DoctorSearchListSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: DoctorSearchListState) {
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
            doctorSearchMode.let { doctorSearchMode ->
                binding.toolbar.favoriteButton.toggleSelection(doctorSearchMode == DoctorSearchListMode.ShowingFavoriteDoctors)
                binding.toolbar.allDoctorsBtn.toggleSelection(doctorSearchMode == DoctorSearchListMode.ShowingAllDoctors)
            }
            filtersEnabled.let {
                binding.toolbar.btnApplyFilters.toggleAvailability(it)
                binding.toolbar.btnClearFilters.toggleAvailability(it)
            }
        }
        /*stateTransformer(state).data?.let {

        }*/
    }

    override fun provideViewModel(): DoctorSearchListViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchListStateTransformer =
        stateTransformerFactory(Unit)

}
