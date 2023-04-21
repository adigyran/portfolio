package com.aya.digital.core.navigation.bottomnavigation

interface BottomNavigationMenuProvider {
    fun getMenu():Int
    fun getFakeBadgeId():Int
}