package com.aya.digital.core.datastore

import androidx.datastore.rxjava3.RxDataStore
import com.aya.digital.core.data.preferences.AuthUserData
import com.aya.digital.core.datastore.UserPreferencesOuterClass.UserPreferences
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.operators.completable.CompletableFromSingle
import kotlinx.coroutines.rx3.await

class HealthAppAuthDataSource constructor(
    private val userPreferences: RxDataStore<UserPreferences>
) {
    val authUserData: Flowable<AuthUserData> = userPreferences.data()
        .map {
            AuthUserData(
                refreshToken = it.refreshToken,
                accessToken = it.accessToken
            )
        }


    val accessTokenData: Flowable<String> = userPreferences.data()
        .map {it.accessToken
        }

    val refreshTokenData: Flowable<String> = userPreferences.data()
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

    suspend fun saveAuthDataSuspend(token :String, refreshTokenIn:String): UserPreferences = userPreferences.updateDataAsync{
        val copy = it.copy {
            accessToken = token
            refreshToken = refreshTokenIn
        }
        Single.just(copy)
    }.await()

    fun clearAuthData() =
        userPreferences.updateDataAsync {
            val copy = it.copy {
                accessToken = ""
                refreshToken = ""
            }
            Single.just(copy)
        }
}