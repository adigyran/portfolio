package com.aya.digital.core.feature.tabviews.doctorsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.feature.tabviews.doctorsearch.databinding.ViewDoctorsearchBinding
import com.aya.digital.core.feature.tabviews.doctorsearch.di.doctorSearchDiModule
import com.aya.digital.core.feature.tabviews.doctorsearch.ui.model.DoctorSearchStateTransformer
import com.aya.digital.core.feature.tabviews.doctorsearch.ui.model.DoctorSearchUiModel
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchState
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchViewModel
import com.aya.digital.core.feature.tabviews.doctorsearch.viewmodel.DoctorSearchSideEffects
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
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


        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }


    override fun provideDiModule(): DI.Module = doctorSearchDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchBinding = ViewDoctorsearchBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: DoctorSearchSideEffects) =
        when(sideEffect)
        {
            is DoctorSearchSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: DoctorSearchState) {
        stateTransformer(state).data?.let {

        }
    }

    override fun provideViewModel(): DoctorSearchViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorSearchStateTransformer =
        stateTransformerFactory(Unit)

}
