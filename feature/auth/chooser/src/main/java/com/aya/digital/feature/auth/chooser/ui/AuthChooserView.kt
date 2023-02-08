package com.aya.digital.feature.auth.chooser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.feature.auth.chooser.databinding.ViewAuthChooserBinding
import com.aya.digital.feature.auth.chooser.di.authChooserDiModule
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserState
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserViewModel
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class AuthChooserView : DiFragment<ViewAuthChooserBinding, AuthChooserViewModel, AuthChooserState, BaseSideEffect>() {

    private val viewModelFactory: ((Unit) -> AuthChooserViewModel) by kodein.on(
        context = this
    ).factory()


    override fun provideDiModule(): DI.Module =  authChooserDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewAuthChooserBinding = ViewAuthChooserBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: AuthChooserState) = Unit

    override fun provideViewModel(): AuthChooserViewModel  = viewModelFactory(Unit)

}
