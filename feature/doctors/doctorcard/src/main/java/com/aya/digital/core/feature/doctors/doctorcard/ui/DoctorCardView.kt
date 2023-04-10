package com.aya.digital.core.feature.doctors.doctorcard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.feature.doctors.doctorcard.databinding.ViewDoctorCardBinding
import com.aya.digital.core.feature.doctors.doctorcard.di.doctorCardDiModule
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardStateTransformer
import com.aya.digital.core.feature.doctors.doctorcard.ui.model.DoctorCardUiModel
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardState
import com.aya.digital.core.feature.doctors.doctorcard.viewmodel.DoctorCardViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class DoctorCardView :
    DiFragment<ViewDoctorCardBinding, DoctorCardViewModel, DoctorCardState, BaseSideEffect, DoctorCardUiModel, DoctorCardStateTransformer>() {

    private val viewModelFactory: ((Unit) -> DoctorCardViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> DoctorCardStateTransformer) by kodein.on(
        context = this
    ).factory()

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

    }

    override fun provideDiModule(): DI.Module = doctorCardDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorCardBinding =
        ViewDoctorCardBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: DoctorCardState) {
        stateTransformer(state).run {

        }
    }

    override fun provideViewModel(): DoctorCardViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): DoctorCardStateTransformer =
        stateTransformerFactory(Unit)

}
