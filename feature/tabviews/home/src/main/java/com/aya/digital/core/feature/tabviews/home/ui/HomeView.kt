package com.aya.digital.core.feature.tabviews.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.tabviews.home.di.homeDiModule
import com.aya.digital.core.feature.tabviews.home.ui.model.HomeStateTransformer
import com.aya.digital.core.feature.tabviews.home.ui.model.HomeUiModel
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.feature.auth.signin.viewmodel.HomeViewModel
import com.aya.digital.core.feature.tabviews.home.databinding.ViewHomeBinding
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeSideEffects
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class HomeView :
    DiFragment<ViewHomeBinding, HomeViewModel, HomeState, HomeSideEffects, HomeUiModel, HomeStateTransformer>() {

    private val viewModelFactory: ((Unit) -> HomeViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> HomeStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
       HomeAdapter(
           onButtonClick = {}
       )
    }

    private lateinit var lm: GridLayoutManager
    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        binding.findDoctorBtn bindClick {viewModel.openDoctors()}
        binding.appointmentsBtn bindClick {viewModel.openAppointments()}
        binding.covidBtn bindClick {viewModel.openAppointments()}
        recyclers.add(binding.rvHome)
        with(binding.rvHome) {
            itemAnimator = null
            setHasFixedSize(false)
            lm = GridLayoutManager(
                context,
                2,
                RecyclerView.VERTICAL,
                false
            )
            layoutManager = lm
            addItemDecoration(HomeTabDecoration(context))

        }
    }


    override fun provideDiModule(): DI.Module = homeDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewHomeBinding = ViewHomeBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: HomeSideEffects) =
        when(sideEffect)
        {
            is HomeSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: HomeState) {
        stateTransformer(state).run {
            data?.let {
                adapter.items = it
                if (binding.rvHome.adapter == null) {
                    binding.rvHome.swapAdapter(adapter, true)
                    lm.spanSizeLookup = HomeSpanSizeLookup(adapter)
                }
            }
        }
    }

    override fun provideViewModel(): HomeViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): HomeStateTransformer =
        stateTransformerFactory(Unit)

}
