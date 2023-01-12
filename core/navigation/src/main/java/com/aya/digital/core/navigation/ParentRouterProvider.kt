package com.aya.digital.core.navigation

import ru.ivan.core.unclassifiedcommonmodels.navigation.coordinator.CoordinatorRouter

fun interface ParentRouterProvider {
    fun getParentRouter(): CoordinatorRouter
}