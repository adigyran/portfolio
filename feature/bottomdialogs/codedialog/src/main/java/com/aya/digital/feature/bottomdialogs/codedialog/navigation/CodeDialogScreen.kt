package com.aya.digital.feature.bottomdialogs.codedialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.codedialog.ui.CodeDialogView

class CodeDialogScreen(tag:String, val requestCode:String, val value:String ) : HealthAppDialogFragmentScreen(tag = tag,fragmentCreator = { CodeDialogView.getNewInstance(requestCode,value) })