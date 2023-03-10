package com.aya.digital.feature.bottomdialogs.bottomdialog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.feature.bottomdialogs.bottomdialog.di.bottomDialogDiModule
import com.aya.digital.feature.bottomdialogs.bottomdialog.viewmodel.BottomDialogState
import com.aya.digital.feature.bottomdialogs.bottomdialog.viewmodel.BottomDialogViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.mvi.BaseUiModel
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.feature.bottomdialogs.bottomdialog.databinding.ViewBottomDialogBinding
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class BottomDialogView :
    DiBottomSheetDialogFragment<ViewBottomDialogBinding, BottomDialogViewModel, BottomDialogState, BaseSideEffect,BaseUiModel,BaseStateTransformer<BottomDialogState,BaseUiModel>>() {

    private val viewModelFactory: ((Unit) -> BottomDialogViewModel) by kodein.on(
        context = this
    ).factory()

    override fun provideViewModel(): BottomDialogViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): BaseStateTransformer<BottomDialogState, BaseUiModel> {
        TODO("Not yet implemented")
    }


    override fun provideDiModule(): DI.Module =  bottomDialogDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewBottomDialogBinding = ViewBottomDialogBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) {
        super.sideEffect(sideEffect)
    }

    override fun render(state: BottomDialogState) = Unit
}
