package com.aya.digital.core.feature.choosers.selectwithsearch.navigation

import com.aya.digital.core.feature.choosers.selectwithsearch.ui.SelectWithSearchView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class SelectWithSearchScreen(val requestCode: String, val isMultiChoose:Boolean, val selectedItems: Set<Int>) :
    HealthAppFragmentScreen(fragmentCreator = {
        SelectWithSearchView.getNewInstance(
            requestCode,
            isMultiChoose,
            selectedItems
        )
    })
