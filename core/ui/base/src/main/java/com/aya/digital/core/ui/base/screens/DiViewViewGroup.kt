package com.aya.digital.core.ui.base.screens

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.ext.strings
import com.aya.digital.core.mvi.*
import com.aya.digital.core.ui.core.CoreViewGroup
import com.jakewharton.rxrelay3.PublishRelay
import org.kodein.di.DI
import org.kodein.di.LateInitDI
import org.kodein.di.LazyDI
import org.orbitmvi.orbit.viewmodel.observe
import java.util.concurrent.TimeUnit

abstract class DiViewViewGroup<Binding : ViewBinding, ViewModel : BaseViewModel<State, SideEffect>, State : BaseState, SideEffect : BaseSideEffect, UiModel : BaseUiModel, StateTransformer : BaseStateTransformer<State, UiModel>> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    CoreViewGroup<Binding>(context, attrs, defStyleAttr) {
    protected val kodein = LateInitDI()
    protected val parentKodein = LateInitDI()

    protected lateinit var viewModel: ViewModel
    protected lateinit var stateTransformer: StateTransformer


    init {
        viewModel = this.provideViewModel()
        stateTransformer = this.provideStateTransformer()
    }



    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
       // viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
    }

    protected fun onBack() {
        viewModel.onBack()
    }

    abstract fun render(state: State)

    abstract fun sideEffect(sideEffect: SideEffect)

    protected fun processErrorSideEffect(errorSideEffect: BaseViewModel.ErrorSideEffect) {
        when (errorSideEffect) {
            is BaseViewModel.ErrorSideEffect.Message -> {
            }

            is BaseViewModel.ErrorSideEffect.MessageResource -> {
            }
        }
    }

    final override fun initDi() {
        parentKodein.baseDI = getClosestParentKodein()
        kodein.baseDI = getClosestParentKodein()
    }

    final override fun createRetainedInstance(): DI = LazyDI {
        DI {
            extend(parentKodein)
            import(provideDiModule(), allowOverride = true)
        }
    }

    abstract fun provideViewModel(): ViewModel

    abstract fun provideStateTransformer(): StateTransformer

    abstract fun provideDiModule(): DI.Module

}