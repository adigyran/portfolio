package com.aya.digital.core.repository.address.lib

import android.os.Looper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

public abstract class MainThreadSingle<T : Any> : Single<T>() {

    override fun subscribeActual(observer: SingleObserver<in T>) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onSubscribe(Disposable.empty())
            observer.onError(NotOnMainThreadException())
            return
        }
        subscribeMainThread(observer)
    }

    /**
     * Called on subscription once thread checks have been performed.
     */
    public abstract fun subscribeMainThread(observer: SingleObserver<in T>)
}
