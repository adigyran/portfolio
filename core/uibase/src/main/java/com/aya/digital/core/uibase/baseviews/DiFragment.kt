package com.aya.digital.core.uibase.baseviews

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import org.kodein.di.DI
import org.kodein.di.LateInitDI
import org.kodein.di.LazyDI
import ru.ivan.core.di.KodeinInjectionManager
import ru.ivan.core.ext.clickWithDebounce
import java.util.concurrent.TimeUnit

abstract class DiFragment<Binding : ViewBinding, ViewModel : Any, UiEvent : Any, News : Any> :
    CoreFragment<Binding>() {
    protected val kodein = LateInitDI()
    protected val parentKodein = LateInitDI()

    protected lateinit var viewModelWatcher: ModelWatcher<ViewModel>
    protected open val viewModelConsumer = Consumer<ViewModel> { viewModelWatcher(it) }
    protected val uiEvents: PublishRelay<UiEvent> = PublishRelay.create()
    protected val newsConsumer = Consumer<News> {
        processNews(it)
    }

    protected val uiEventsObservableSource = ObservableSource<UiEvent> { uiEvents.subscribe(it) }

    protected lateinit var timeCapsule: AndroidTimeCapsule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timeCapsule = AndroidTimeCapsule(savedInstanceState)
        provideBindings().setup(viewModelConsumer, uiEventsObservableSource, newsConsumer)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.saveState(outState)
    }

    protected open fun showErrorMsg(message: String) =
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

    protected fun EditText.addDelayedTextChangedListener(
        beforeTextChanged: () -> Unit,
        afterTextChanged: (text: String) -> Unit,
    ) {
        this.addTextChangedListener(DelayedTextChangeWatcher(beforeTextChanged, afterTextChanged))
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

        viewModelWatcher = provideModelWatcher()
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

    abstract fun provideModelWatcher(): ModelWatcher<ViewModel>

    abstract fun provideBindings(): BindingsBase<ViewModel, UiEvent, News>

    abstract fun provideDiModule(): DI.Module

    abstract fun processNews(news: News)

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