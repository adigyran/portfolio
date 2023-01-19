package com.aya.digital.core.navigation.utils

import com.aya.digital.core.navigation.coordinator.CoordinatorRouter

fun interface ParentRouterProvider {
    fun getParentRouter(): CoordinatorRouter
}