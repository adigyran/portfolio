package com.aya.digital.core.datastore

import androidx.datastore.rxjava3.RxDataStore
import com.aya.digital.core.data.preferences.UserData
import com.aya.digital.core.datastore.UserPreferencesOuterClass.UserPreferences

class HealthAppDataSource constructor(
    private val userPreferences: RxDataStore<UserPreferences>
) {
    val userData = userPreferences.data()
        .map {
            com.aya.digital.core.data.preferences.UserData(
                onBoardingSeen = it.seenOnBoarding,
                disclaimerSeen = it.seenDisclaimer
            )
        }
}