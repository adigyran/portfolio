package com.aya.digital.core.datastore

import androidx.datastore.rxjava3.RxDataStore
import com.aya.digital.core.data.preferences.AuthUserData
import com.aya.digital.core.datastore.UserPreferencesOuterClass.UserPreferences
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.operators.completable.CompletableFromSingle

class HealthAppAuthDataSource constructor(
    private val userPreferences: RxDataStore<UserPreferences>
) {
    val authUserData = userPreferences.data()
        .map {
            com.aya.digital.core.data.preferences.AuthUserData(
                refreshToken = it.refreshToken,
                accessToken = it.accessToken,
                idToken = it.tokenId
            )
        }


    fun saveAuthData(authUserData: com.aya.digital.core.data.preferences.AuthUserData) =
        CompletableFromSingle(userPreferences.updateDataAsync {
            val copy = it.copy {
                tokenId = authUserData.idToken
                accessToken = authUserData.accessToken
                refreshToken = authUserData.refreshToken
            }
            Single.just(copy)
        })

    fun clearAuthData() =
        CompletableFromSingle(userPreferences.updateDataAsync {
            val copy = it.copy {
                tokenId = ""
                accessToken = ""
                refreshToken = ""
            }
            Single.just(copy)
        })
}