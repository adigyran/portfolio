package com.aya.digital.core.mvi

import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.Consumer


abstract class BindingsBase<ViewModel : Any, UiEvent : Any, News : Any> {
    abstract fun setup(
        viewModelConsumer: Consumer<ViewModel>, uiEventsObservableSource: ObservableSource<UiEvent>,
        newsConsumer: Consumer<News>,
    )
}