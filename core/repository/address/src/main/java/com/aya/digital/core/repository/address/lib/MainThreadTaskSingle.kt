package com.aya.digital.core.repository.address.lib

import com.google.android.gms.tasks.CancellationTokenSource
import io.reactivex.rxjava3.core.SingleObserver

internal abstract class MainThreadTaskSingle<T : Any> : MainThreadSingle<T>() {
    override fun subscribeMainThread(observer: SingleObserver<in T>) {
        val cancellationTokenSource = CancellationTokenSource()
        val listener = TaskCompletionListener(cancellationTokenSource, observer)
        invokeRequest(listener)
        observer.onSubscribe(listener)
    }

    abstract fun invokeRequest(listener: TaskCompletionListener<T>)
}