package com.aya.digital.core.datastore

import androidx.datastore.core.DataStore

class PatientAppPreferencesDataSource constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
}