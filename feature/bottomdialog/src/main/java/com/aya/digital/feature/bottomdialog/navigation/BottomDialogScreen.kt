package com.aya.digital.feature.bottomdialog.navigation

import com.aya.digital.core.navigation.screen.HealthAppDialogFragmentScreen
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.bottomdialog.ui.BottomDialogView
import com.github.terrakok.cicerone.androidx.FragmentScreen

class BottomDialogScreen(tag : String) : HealthAppDialogFragmentScreen(tag = tag,fragmentCreator = {BottomDialogView()})