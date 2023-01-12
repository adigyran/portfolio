package com.aya.digital.core.navigation

import com.github.terrakok.cicerone.Router

fun interface NavigationRouterProvider {
    fun getNavigationRouter(): Router
}