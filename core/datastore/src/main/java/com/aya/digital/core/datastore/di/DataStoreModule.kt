package com.aya.digital.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.rxjava3.RxDataStore
import androidx.datastore.rxjava3.RxDataStoreBuilder
import androidx.datastore.rxjava3.rxDataStore
import com.aya.digital.core.datastore.PatientAppDataSource
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

    bind<PatientAppDataSource>() with eagerSingleton {
        PatientAppDataSource(instance())
    }
}