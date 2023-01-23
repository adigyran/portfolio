package com.aya.digital.core.uibase.baseviews

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.aya.digital.base.appbase.BaseApp
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.utils.ChildKodeinProvider
import com.aya.digital.core.navigation.utils.ParentRouterProvider
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.uibase.core.CoreActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import org.kodein.di.*

abstract class DiActivity<Binding : ViewBinding, out ViewModel : BaseViewModel<State, SideEffect>, State : Parcelable, SideEffect : BaseSideEffect> :
    CoreActivity<Binding>(), ChildKodeinProvider, ParentRouterProvider {
    protected val kodein = LateInitDI()

    protected abstract val viewModel: ViewModel
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

        coordinatorHolder.setCoordinator(coordinator)
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


    override fun prepareUi() {
        super.prepareUi()
    }

    final override fun initDi() {
        kodein.baseDI = KodeinInjectionManager.instance.bindKodein(this)
    }

    final override fun getChildKodein(): DI = kodein
    final override fun getParentRouter(): CoordinatorRouter = coordinatorHolder

    abstract fun provideNavigator(): Navigator

    abstract fun provideCoordinator(): Coordinator

    abstract fun provideDiModule(): DI.Module

}