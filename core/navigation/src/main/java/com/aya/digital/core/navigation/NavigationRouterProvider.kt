package com.aya.digital.core.navigation

fun interface NavigationRouterProvider {
    fun getNavigationRouter(): Router
}