package com.aya.digital.core.navigation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

abstract class HealthAppFragmentScreen(
    key: String? = null,
    fragmentCreator: Creator<FragmentFactory, Fragment>
) : FragmentScreen(key, fragmentCreator)