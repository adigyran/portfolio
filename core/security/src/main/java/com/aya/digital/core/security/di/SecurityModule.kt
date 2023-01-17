package com.aya.digital.core.security.di

import com.aya.digital.core.security.CipherProvider
import com.aya.digital.core.security.Crypto
import com.aya.digital.core.security.CryptoImpl
import com.aya.digital.core.security.SecurityConst
import com.stylingandroid.datastore.security.AesCipherProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import java.security.KeyStore

fun securityDiModule() = DI.Module("securityDiModule") {
    bind<KeyStore>() with eagerSingleton {
        KeyStore.getInstance(SecurityConst.ANDROID_KEY_STORE_TYPE).apply { load(null) }
    }

    bind<KeyStore>() with eagerSingleton {
        KeyStore.getInstance(SecurityConst.ANDROID_KEY_STORE_TYPE).apply { load(null) }
    }

    bind<CipherProvider>() with eagerSingleton {
        AesCipherProvider(SecurityConst.KEY_NAME,instance(),SecurityConst.KEY_STORE_NAME)
    }

    bind<Crypto>() with eagerSingleton {
        CryptoImpl(instance())
    }
}
