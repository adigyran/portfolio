package com.aya.digital.core.datastore

import androidx.datastore.core.DataStore
import com.aya.digital.core.datastore.UserPreferencesOuterClass.UserPreferences

class PatientAppPreferencesDataSource constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
}