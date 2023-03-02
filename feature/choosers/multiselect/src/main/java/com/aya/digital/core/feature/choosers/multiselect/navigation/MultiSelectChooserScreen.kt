package com.aya.digital.core.feature.choosers.multiselect.navigation

import com.aya.digital.core.feature.choosers.multiselect.ui.MultiSelectChooserView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class MultiSelectChooserScreen(val requestCode:String, val selectedItems:Set<Int>) : HealthAppFragmentScreen(fragmentCreator = { MultiSelectChooserView.getNewInstance(requestCode, selectedItems) })
