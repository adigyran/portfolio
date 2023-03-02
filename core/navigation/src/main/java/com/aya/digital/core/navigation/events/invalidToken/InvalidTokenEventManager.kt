package com.aya.digital.core.navigation.events.invalidToken

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable

interface InvalidTokenEventManager {
    fun onInvalidToken()
    fun subscribeToEvent(): Observable<Boolean>
}