package com.aya.digital.feature.bottomdialogs.bottomdialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.feature.bottomdialogs.bottomdialog.ui.BottomDialogView

class BottomDialogScreen(tag : String) : HealthAppDialogFragmentScreen(tag = tag,fragmentCreator = { BottomDialogView() })