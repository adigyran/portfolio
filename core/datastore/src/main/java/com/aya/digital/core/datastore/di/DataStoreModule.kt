package com.aya.digital.core.datastore.di

import androidx.datastore.rxjava3.RxDataStore
import androidx.datastore.rxjava3.RxDataStoreBuilder
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.datastore.HealthAppDataSource
import com.aya.digital.core.datastore.UserPreferencesOuterClass.UserPreferences
import com.aya.digital.core.datastore.UserPreferencesSerializer
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

fun dataStoreDiModule() = DI.Module("dataStoreDiModule") {
    bind<UserPreferencesSerializer>() with eagerSingleton {
        UserPreferencesSerializer(instance())
    }

    bind<RxDataStore<UserPreferences>>() with eagerSingleton {
       RxDataStoreBuilder(context = instance(),"user_preferences.pb",instance<UserPreferencesSerializer>()).build()
    }

    bind<HealthAppDataSource>() with eagerSingleton {
        HealthAppDataSource(instance())
    }

    bind<HealthAppAuthDataSource>() with eagerSingleton {
        HealthAppAuthDataSource(instance())
    }
}