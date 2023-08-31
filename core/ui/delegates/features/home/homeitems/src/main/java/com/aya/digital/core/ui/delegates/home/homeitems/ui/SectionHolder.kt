package com.aya.digital.core.ui.delegates.home.homeitems.ui


interface SectionHolder {
    fun getHomeSection():HomeSection
}

sealed class HomeSection{
    object Buttons:HomeSection()
    object News:HomeSection()
    object Other:HomeSection()
}