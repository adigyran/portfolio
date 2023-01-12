package com.aya.digital.core.uibase.baseviews

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.aya.digital.core.dibase.KodeinInjectionManager
import com.aya.digital.core.ext.clickWithDebounce
import com.aya.digital.core.mvi.BaseFeature
import com.aya.digital.core.mvi.BindingsBase
import com.aya.digital.core.navigation.ChildKodeinProvider
import com.aya.digital.core.navigation.ParentRouterProvider
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.uibase.core.CoreActivity
import com.aya.digital.core.util.BaseApp
import com.badoo.mvicore.ModelWatcher
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.Consumer
import org.kodein.di.*

abstract class DiActivity<Binding : ViewBinding, ViewModel : Any, UiEvent : Any, News : Any> :
    CoreActivity<Binding>(), ChildKodeinProvider, ParentRouterProvider {
    protected val kodein = LateInitDI()

    protected lateinit var viewModelWatcher: ModelWatcher<ViewModel>
    protected open val viewModelConsumer = Consumer<ViewModel> { viewModelWatcher(it) }
    private val uiEvents: PublishRelay<UiEvent> = PublishRelay.create()
    protected val newsConsumer = Consumer<News> {
        processNews(it)
    }

    protected val uiEventsObservableSource = ObservableSource<UiEvent> { uiEvents.subscribe(it) }

    protected val localCicerone: Cicerone<Router> by kodein.on(context = this as Activity)
        .instance()
    protected val coordinatorHolder: CoordinatorHolder by kodein.on(context = this as Activity)
        .instance()

    override val permissionManager: PermissionsManager by kodein.on(context = this as Activity)
        .instance()
    override val activityResultManager: ActivityResultManager by kodein.on(context = this as Activity)
        .instance()

    lateinit var coordinator: Coordinator
    lateinit var navigator: Navigator
    //endregion


    //region Перегруженные методы
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = provideNavigator()
        coordinator = provideCoordinator()

        provideBindings().setup(viewModelConsumer, uiEventsObservableSource, newsConsumer)

        coordinatorHolder.setCoordinator(coordinator)
    }

    override fun onPause() {
        localCicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        localCicerone.navigatorHolder.setNavigator(navigator)
    }


    override fun onDestroy() {
        coordinatorHolder.removeCoordinator()
        super.onDestroy()
    }

    protected fun showErrorMsg(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    protected fun processBaseNews(news: BaseFeature.BaseNews) {
        when (news) {
            is BaseFeature.BaseNews.Message -> showErrorMsg(news.msg)
        }
    }

    final override fun createRetainedInstance(): DI = LazyDI {
        DI {
            extend(BaseApp.appInstance.di)
            import(provideDiModule(), allowOverride = true)
        }
    }

    protected fun sendUiEvent(event: UiEvent) = runOnUiThread { uiEvents.accept(event) }

    protected infix fun View.bindEvent(action: () -> UiEvent) {
        this.clickWithDebounce(action = { uiEvents.accept(action()) })
    }

    override fun prepareUi() {
        super.prepareUi()

        viewModelWatcher = provideModelWatcher()
    }

    final override fun initDi() {
        kodein.baseDI = KodeinInjectionManager.instance.bindKodein(this)
    }

    final override fun getChildKodein(): DI = kodein
    final override fun getParentRouter(): CoordinatorRouter = coordinatorHolder

    abstract fun provideNavigator(): Navigator

    abstract fun provideCoordinator(): Coordinator

    abstract fun provideModelWatcher(): ModelWatcher<ViewModel>

    abstract fun provideBindings(): BindingsBase<ViewModel, UiEvent, News>

    abstract fun provideDiModule(): DI.Module

    abstract fun processNews(news: News)
}