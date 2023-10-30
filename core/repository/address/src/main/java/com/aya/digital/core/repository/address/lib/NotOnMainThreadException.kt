package com.aya.digital.core.repository.address.lib

public class NotOnMainThreadException : IllegalStateException(
    "Subscription is on ${Thread.currentThread().name} but must be on main thread."
)