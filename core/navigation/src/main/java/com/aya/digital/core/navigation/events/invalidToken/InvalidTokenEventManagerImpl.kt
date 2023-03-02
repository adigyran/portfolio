package com.aya.digital.core.navigation.events.invalidToken

import com.jakewharton.rxrelay3.PublishRelay

internal class InvalidTokenEventManagerImpl : InvalidTokenEventManager {

    val eventSubject = PublishRelay.create<Boolean>()

    override fun onInvalidToken() {
        eventSubject.accept(true)
    }

    override fun subscribeToEvent() = eventSubject.cache()
}