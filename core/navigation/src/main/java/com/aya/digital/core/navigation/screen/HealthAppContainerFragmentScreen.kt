package com.aya.digital.core.navigation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator

abstract class HealthAppContainerFragmentScreen(
    key: String? = null,
    fragmentCreator: Creator<FragmentFactory, Fragment>
) : HealthAppFragmentScreen(key, fragmentCreator)