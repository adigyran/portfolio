package com.aya.digital.core.security.di

import com.aya.digital.core.security.CipherProvider
import com.aya.digital.core.security.Crypto
import com.aya.digital.core.security.CryptoImpl
import com.aya.digital.core.security.SecurityConst
import com.stylingandroid.datastore.security.AesCipherProvider
import org.kodein.di.*
import java.security.KeyStore

fun securityDiModule() = DI.Module("securityDiModule") {
    bind<KeyStore>() with singleton {
        KeyStore.getInstance(SecurityConst.ANDROID_KEY_STORE_TYPE).apply { load(null) }
    }

    bind<CipherProvider>() with singleton {
        AesCipherProvider(SecurityConst.KEY_NAME,instance(),SecurityConst.KEY_STORE_NAME)
    }

    bind<Crypto>() with singleton {
        CryptoImpl(instance())
    }
}
