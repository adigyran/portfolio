package com.aya.digital.core.feature.tabviews.doctorsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.tabviews.doctorsearch.databinding.ViewDoctorsearchBinding
import com.aya.digital.core.feature.tabviews.doctorsearch.di.doctorSearchDiModule
import com.aya.digital.core.feature.tabviews.doctorsearch.ui.model.DoctorSearchStateTransformer
import com.aya.digital.core.feature.tabviews.doctorsearch.ui.model.DoctorSearchUiModel
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchSideEffects
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchState
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.EndlessRecyclerViewScrollListener
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorSearchView :
    DiFragment<ViewDoctorsearchBinding, DoctorSearchViewModel, DoctorSearchState, DoctorSearchSideEffects, DoctorSearchUiModel, DoctorSearchStateTransformer>() {

    private val viewModelFactory: ((Unit) -> DoctorSearchViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorSearchStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                DoctorItemDelegate(viewModel::onDoctorClicked,viewModel::onDoctorFavouriteClicked)
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
        binding.toolbar.insurance bindClick {viewModel.onInsurance()}
        binding.toolbar.speciality bindClick {viewModel.onSpecialisation()}
        binding.toolbar.location bindClick {viewModel.onLocation()}
        if (savedInstanceState == null) {
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
                addItemDecoration(DoctorSearchTabDecoration())
                val scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
                    override fun onLoadMore() {
                        viewModel.loadNextPage()
                    }
                }
                addOnScrollListener(scrollListener)
            }
        }
    }


    override fun provideDiModule(): DI.Module = doctorSearchDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchBinding = ViewDoctorsearchBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: DoctorSearchSideEffects) =
        when (sideEffect) {
            is DoctorSearchSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: DoctorSearchState) {
        stateTransformer(state).run {
            data?.let {  adapter.items = it
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                } }
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

    override fun provideViewModel(): DoctorSearchViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchStateTransformer =
        stateTransformerFactory(Unit)

}
