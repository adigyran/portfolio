package com.aya.digital.core.uibase.baseviews

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.ext.clickWithDebounce
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.utils.ChildKodeinProvider
import com.aya.digital.core.navigation.utils.ParentRouterProvider
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.uibase.core.CoreContainerFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import org.kodein.di.*
import org.orbitmvi.orbit.viewmodel.observe

abstract class DiContainerFragment<Binding : ViewBinding, out ViewModel : BaseViewModel<State, SideEffect>, State : Parcelable, SideEffect : BaseSideEffect> :
    CoreContainerFragment<Binding>(),
    ChildKodeinProvider, ParentRouterProvider {
    protected val kodein = LateInitDI()
    protected val parentKodein = LateInitDI()

    protected abstract val viewModel: ViewModel

    override val coordinatorHolder: CoordinatorHolder by kodein.on(context = this as Fragment)
        .instance()
    override val coordinator: Coordinator by kodein.on(context = this as Fragment).instance()
    override val localCicerone: Cicerone<Router> by kodein.on(context = this as Fragment).instance()

    override lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = provideNavigator()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    protected fun showErrorMsg(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
    }

    abstract fun render(state: State)

    abstract fun sideEffect(sideEffect: SideEffect)

    final override fun initDi() {
        parentKodein.baseDI = getClosestParentKodein()
        kodein.baseDI = KodeinInjectionManager.instance.bindKodein(this)
    }

    final override fun getChildKodein(): DI = kodein
    final override fun getParentRouter(): CoordinatorRouter = coordinatorHolder

    final override fun createRetainedInstance(): DI = LazyDI {
        DI {
            extend(parentKodein)
            import(provideDiModule(), allowOverride = true)
        }
    }

    abstract fun provideNavigator(): Navigator

    abstract fun provideDiModule(): DI.Module
}