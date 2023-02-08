package com.aya.digital.core.datastore.di

import androidx.datastore.rxjava3.RxDataStore
import androidx.datastore.rxjava3.RxDataStoreBuilder
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.datastore.HealthAppDataSource
import com.aya.digital.core.datastore.UserPreferencesOuterClass.UserPreferences
import com.aya.digital.core.datastore.UserPreferencesSerializer
import org.kodein.di.*

fun dataStoreDiModule() = DI.Module("dataStoreDiModule") {
    bind<UserPreferencesSerializer>() with singleton {
        UserPreferencesSerializer(instance())
    }

    bind<RxDataStore<UserPreferences>>() with singleton {
       RxDataStoreBuilder(context = instance(),"user_preferences.pb",instance<UserPreferencesSerializer>()).build()
    }

    bind<HealthAppDataSource>() with singleton {
        HealthAppDataSource(instance())
    }

    bind<HealthAppAuthDataSource>() with singleton {
        HealthAppAuthDataSource(instance())
    }
}