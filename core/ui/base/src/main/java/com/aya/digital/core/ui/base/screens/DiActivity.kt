package com.aya.digital.core.ui.base.screens

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.ext.strings
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseState
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.utils.BackButtonListener
import com.aya.digital.core.navigation.utils.ChildKodeinProvider
import com.aya.digital.core.navigation.utils.ParentRouterProvider
import com.aya.digital.core.ui.core.CoreActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import org.kodein.di.*
import org.orbitmvi.orbit.viewmodel.observe

abstract class DiActivity<Binding : ViewBinding,ViewModel : BaseViewModel<State, SideEffect>, State : BaseState, SideEffect : BaseSideEffect> :
    CoreActivity<Binding>(), ChildKodeinProvider, ParentRouterProvider {
    protected val kodein = LateInitDI()

    protected lateinit var viewModel: ViewModel
    protected val localCicerone: Cicerone<Router> by kodein.on(context = this as Activity)
        .instance()
    protected val coordinatorHolder: CoordinatorHolder by kodein.on(context = this as Activity)
        .instance()

    lateinit var coordinator: Coordinator
    lateinit var navigator: Navigator
    //endregion


    //region Перегруженные методы
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = provideNavigator()
        coordinator = provideCoordinator()
        viewModel = provideViewModel()

        coordinatorHolder.setCoordinator(coordinator)
        coordinatorHolder.setRouter(localCicerone.router)
        viewModel.observe(this, state = ::render, sideEffect = ::sideEffect)
    }


    override fun onPause() {
        localCicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        localCicerone.getNavigatorHolder().setNavigator(navigator)
    }


    override fun onDestroy() {
        coordinatorHolder.removeCoordinator()
        coordinatorHolder.removeRouter()
        super.onDestroy()
    }

    protected fun showErrorMsg(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    final override fun createRetainedInstance(): DI = LazyDI {
        DI {
            extend(BaseApp.appInstance.di)
            import(provideDiModule(), allowOverride = true)
        }
    }

    abstract fun render(state: State)

    abstract  fun sideEffect(sideEffect: SideEffect)

    protected fun processErrorSideEffect(errorSideEffect: BaseViewModel.ErrorSideEffect){
        when(errorSideEffect)
        {
            is BaseViewModel.ErrorSideEffect.Message -> {showErrorMsg(errorSideEffect.msg)}
            is BaseViewModel.ErrorSideEffect.MessageResource -> {showErrorMsg(strings[errorSideEffect.msgResource])}
        }
    }

    override fun prepareUi() {
        super.prepareUi()
    }



    final override fun initDi() {
        kodein.baseDI = KodeinInjectionManager.instance.bindKodein(this)
    }

    override fun onBackPressed() {
        val fragment = getVisibleFragment()
        if ((fragment as? BackButtonListener)?.onBackPressed() == true) {
            return
        }
        localCicerone.router.exit()
    }
    private fun getVisibleFragment(): Fragment? {
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) return fragment
        }
        return null
    }

    final override fun getChildKodein(): DI = kodein
    final override fun getParentRouter(): CoordinatorRouter = coordinatorHolder

    abstract fun provideViewModel(): ViewModel

    abstract fun provideNavigator(): Navigator

    abstract fun provideCoordinator(): Coordinator

    abstract fun provideDiModule(): DI.Module

}