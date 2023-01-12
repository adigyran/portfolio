package com.aya.digital.core.navigation

import ru.terrakok.cicerone.Router

fun interface NavigationRouterProvider {
    fun getNavigationRouter(): Router
}