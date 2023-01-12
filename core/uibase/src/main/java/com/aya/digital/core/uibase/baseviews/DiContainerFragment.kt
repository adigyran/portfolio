package com.aya.digital.core.uibase.baseviews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.kodein.di.*
import ru.ivan.core.di.KodeinInjectionManager
import ru.ivan.core.ext.clickWithDebounce
import ru.ivan.core.unclassifiedcommonmodels.CustomNavigator
import ru.ivan.core.unclassifiedcommonmodels.navigation.ChildKodeinProvider
import ru.ivan.core.unclassifiedcommonmodels.navigation.ParentRouterProvider
import ru.ivan.core.unclassifiedcommonmodels.navigation.coordinator.Coordinator
import ru.ivan.core.unclassifiedcommonmodels.navigation.coordinator.CoordinatorHolder
import ru.ivan.core.unclassifiedcommonmodels.navigation.coordinator.CoordinatorRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

abstract class DiContainerFragment<Binding : ViewBinding, ViewModel : Any, UiEvent : Any, News : Any> :
    CoreContainerFragment<Binding>(),
    ChildKodeinProvider, ParentRouterProvider {
    protected val kodein = LateInitDI()
    protected val parentKodein = LateInitDI()

    override val coordinatorHolder: CoordinatorHolder by kodein.on(context = this as Fragment)
        .instance()
    override val coordinator: Coordinator by kodein.on(context = this as Fragment).instance()
    override val localCicerone: Cicerone<Router> by kodein.on(context = this as Fragment).instance()

    override lateinit var navigator: CustomNavigator

    protected lateinit var viewModelWatcher: ModelWatcher<ViewModel>
    protected open val viewModelConsumer = Consumer<ViewModel> { viewModelWatcher(it) }
    private val uiEvents: PublishRelay<UiEvent> = PublishRelay.create()
    protected val newsConsumer = Consumer<News> {
        processNews(it)
    }

    protected val uiEventsObservableSource = ObservableSource<UiEvent> { uiEvents.subscribe(it) }

    protected lateinit var timeCapsule: AndroidTimeCapsule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timeCapsule = AndroidTimeCapsule(savedInstanceState)
        navigator = provideNavigator()
        provideBindings().setup(viewModelConsumer, uiEventsObservableSource, newsConsumer)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.saveState(outState)
    }

    protected fun showErrorMsg(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    protected fun processBaseNews(news: BaseFeature.BaseNews) {
        when (news) {
            is BaseFeature.BaseNews.Message -> showErrorMsg(news.msg)
        }
    }

    protected fun sendUiEvent(event: UiEvent) = activity?.runOnUiThread { uiEvents.accept(event) }

    protected infix fun View.bindEvent(action: () -> UiEvent) {
        this.clickWithDebounce(action = { uiEvents.accept(action()) })
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

        viewModelWatcher = provideModelWatcher()
    }

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

    abstract fun provideModelWatcher(): ModelWatcher<ViewModel>

    abstract fun provideBindings(): BindingsBase<ViewModel, UiEvent, News>

    abstract fun provideNavigator(): CustomNavigator

    abstract fun provideDiModule(): DI.Module

    abstract fun processNews(news: News)
}