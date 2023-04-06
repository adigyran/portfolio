package com.aya.digital.core.feature.tabviews.appointments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.feature.tabviews.appointments.databinding.ViewAppointmentsBinding
import com.aya.digital.core.feature.tabviews.appointments.di.appointmentsDiModule
import com.aya.digital.core.feature.tabviews.appointments.ui.model.AppointmentsStateTransformer
import com.aya.digital.core.feature.tabviews.appointments.ui.model.AppointmentsUiModel
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsState
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsViewModel
import com.aya.digital.core.feature.tabviews.appointments.viewmodel.AppointmentsSideEffects
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class AppointmentsView :
    DiFragment<ViewAppointmentsBinding, AppointmentsViewModel, AppointmentsState, AppointmentsSideEffects, AppointmentsUiModel, AppointmentsStateTransformer>() {

    private val viewModelFactory: ((Unit) -> AppointmentsViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> AppointmentsStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {


        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }


    override fun provideDiModule(): DI.Module = appointmentsDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewAppointmentsBinding = ViewAppointmentsBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: AppointmentsSideEffects) =
        when(sideEffect)
        {
            is AppointmentsSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }

    override fun render(state: AppointmentsState) {
        stateTransformer(state).data?.let {

        }
    }

    override fun provideViewModel(): AppointmentsViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): AppointmentsStateTransformer =
        stateTransformerFactory(Unit)

}
