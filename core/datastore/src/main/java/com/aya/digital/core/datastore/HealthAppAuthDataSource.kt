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
            AuthUserData(
                refreshToken = it.refreshToken,
                accessToken = it.accessToken
            )
        }


    val accessTokenData = userPreferences.data()
        .map {it.accessToken
        }
    val refreshTokenData = userPreferences.data()
        .map { it.refreshToken }
    fun saveAuthData(authUserData: AuthUserData) =
        CompletableFromSingle(userPreferences.updateDataAsync {
            val copy = it.copy {
                accessToken = authUserData.accessToken
                refreshToken = authUserData.refreshToken
            }
            Single.just(copy)
        })

    fun saveAuthData(token :String, refreshTokenIn:String) = userPreferences.updateDataAsync{
        val copy = it.copy {
            accessToken = token
            refreshToken = refreshTokenIn
        }
        Single.just(copy)
    }


    fun clearAuthData() =
        CompletableFromSingle(userPreferences.updateDataAsync {
            val copy = it.copy {
                accessToken = ""
                refreshToken = ""
            }
            Single.just(copy)
        })
}