package com.aya.digital.core.ui.base.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.ext.strings
import com.aya.digital.core.mvi.*
import com.aya.digital.core.ui.core.CoreFragment
import com.jakewharton.rxrelay3.PublishRelay
import org.kodein.di.DI
import org.kodein.di.LateInitDI
import org.kodein.di.LazyDI
import org.orbitmvi.orbit.viewmodel.observe
import java.util.concurrent.TimeUnit

abstract class DiFragment<Binding : ViewBinding, ViewModel : BaseViewModel<State, SideEffect>, State : BaseState, SideEffect : BaseSideEffect, UiModel : BaseUiModel, StateTransformer : BaseStateTransformer<State, UiModel>> :
    CoreFragment<Binding>() {
    protected val kodein = LateInitDI()
    protected val parentKodein = LateInitDI()

    protected lateinit var viewModel: ViewModel
    protected lateinit var stateTransformer: StateTransformer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        stateTransformer = provideStateTransformer()
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    protected open fun showErrorMsg(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()


    protected fun EditText.addDelayedTextChangedListener(
        beforeTextChanged: () -> Unit,
        afterTextChanged: (text: String) -> Unit,
    ) {
        this.addTextChangedListener(DelayedTextChangeWatcher(beforeTextChanged, afterTextChanged))
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
    }

    abstract fun render(state: State)

    abstract fun sideEffect(sideEffect: SideEffect)

    protected fun processErrorSideEffect(errorSideEffect: BaseViewModel.ErrorSideEffect){
        when(errorSideEffect)
        {
            is BaseViewModel.ErrorSideEffect.Message -> {showErrorMsg(errorSideEffect.msg)}
            is BaseViewModel.ErrorSideEffect.MessageResource -> {showErrorMsg(strings[errorSideEffect.msgResource])}
        }
    }

    final override fun initDi() {
        parentKodein.baseDI = getClosestParentKodein()
        kodein.baseDI = KodeinInjectionManager.instance.bindKodein(this)
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

    private class DelayedTextChangeWatcher(
        private val beforeTextChanged: () -> Unit,
        private val afterTextChanged: (text: String) -> Unit,
    ) : TextWatcher {
        private val textChangeRelay: PublishRelay<String> = PublishRelay.create()
        private var firstCall = true

        init {
            textChangeRelay.debounce(500, TimeUnit.MILLISECONDS).doOnNext { afterTextChanged(it) }
                .subscribe()
        }

        override fun afterTextChanged(s: Editable) {
            textChangeRelay.accept(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (firstCall) {
                firstCall = false
                return
            }
            beforeTextChanged()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    }
}