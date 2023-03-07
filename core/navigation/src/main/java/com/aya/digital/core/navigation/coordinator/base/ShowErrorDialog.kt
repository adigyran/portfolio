package com.aya.digital.core.navigation.coordinator.base

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

data class ShowErrorDialog(val errorTitleRes:Int,val errorDescriptionRes:Int) : CoordinatorEvent()
