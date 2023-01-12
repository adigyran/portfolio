package com.aya.digital.core.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorRouter

fun interface ParentRouterProvider {
    fun getParentRouter(): CoordinatorRouter
}