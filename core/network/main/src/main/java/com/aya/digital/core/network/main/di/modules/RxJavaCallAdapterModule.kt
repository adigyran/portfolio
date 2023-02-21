package com.aya.digital.core.network

import com.aya.digital.core.network.main.Constants
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

internal fun rxJavaCallAdapterModule() = DI.Module("rxJavaCallAdapterModule") {
    bind<RxJava3CallAdapterFactory>(Constants.RXJAVA_CALL_ADAPTER_TAG) with singleton {
        return@singleton RxJava3CallAdapterFactory.create()
    }
}