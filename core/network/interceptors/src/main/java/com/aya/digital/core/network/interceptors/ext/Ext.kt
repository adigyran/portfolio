package com.aya.digital.core.network.interceptors.ext

import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.network.interceptors.InterceptorsConstants.BEARER

//        val accessToken = authDataSource.authUserData.doOnError { it.printStackTrace() }.blockingFirst().accessToken

fun HealthAppAuthDataSource.accessTokenBlocking() = this.authUserData.doOnError { it.printStackTrace() }.blockingFirst().accessToken
fun HealthAppAuthDataSource.refreshTokenBlocking() = this.authUserData.doOnError { it.printStackTrace() }.blockingFirst().accessToken
fun HealthAppAuthDataSource.saveTokensBlocking(accessToken:String, refreshToken:String) = this.saveAuthData(accessToken,refreshToken).doOnError { it.printStackTrace() }.blockingGet()
fun HealthAppAuthDataSource.bearerTokenBlocking() = this.accessTokenBlocking().run { return@run if(this.isNotBlank()) "%s %s".format(BEARER,this) else "" }