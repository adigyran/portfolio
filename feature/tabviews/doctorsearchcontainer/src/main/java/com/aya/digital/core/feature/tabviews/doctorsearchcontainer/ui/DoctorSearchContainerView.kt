package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.toggleSelection
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.DoctorSearchMode
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.databinding.ViewDoctorsearchContainerBinding
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.di.doctorSearchContainerDiModule
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.model.DoctorSearchContainerStateTransformer
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.model.DoctorSearchContainerUiModel
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerSideEffects
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerState
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerViewModel
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.EndlessRecyclerViewScrollListener
import com.aya.digital.core.ui.delegates.doctors.doctoritem.ui.DoctorItemDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorSearchContainerView :
    DiFragment<ViewDoctorsearchContainerBinding, DoctorSearchContainerViewModel, DoctorSearchContainerState, DoctorSearchContainerSideEffects, DoctorSearchContainerUiModel, DoctorSearchContainerStateTransformer>() {

    private val viewModelFactory: ((Unit) -> DoctorSearchContainerViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorSearchContainerStateTransformer) by kodein.on(
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
        binding.allDoctorsBtn bindClick { viewModel.onAllDoctorsClicked() }
        binding.favoriteButton bindClick { viewModel.onFavoriteDoctorsClicked() }
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
            addItemDecoration(DoctorSearchContainerTabDecoration())
            val scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
                override fun onLoadMore() {
                    viewModel.loadNextPage()
                }
            }
            addOnScrollListener(scrollListener)
        }
    }


    override fun provideDiModule(): DI.Module = doctorSearchContainerDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchContainerBinding = ViewDoctorsearchContainerBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: DoctorSearchContainerSideEffects) =
        when (sideEffect) {
            is DoctorSearchContainerSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: DoctorSearchContainerState) {
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
                binding.favoriteButton.toggleSelection(doctorSearchMode == DoctorSearchMode.ShowingFavoriteDoctors)
                binding.allDoctorsBtn.toggleSelection(doctorSearchMode == DoctorSearchMode.ShowingAllDoctors)
            }
        }
        /*stateTransformer(state).data?.let {

        }*/
    }

    override fun provideViewModel(): DoctorSearchContainerViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchContainerStateTransformer =
        stateTransformerFactory(Unit)

}
