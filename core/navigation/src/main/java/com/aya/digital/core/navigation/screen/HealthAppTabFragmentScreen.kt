package com.aya.digital.core.navigation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator

abstract class HealthAppTabFragmentScreen(
    key: String? = null,
    fragmentCreator: Creator<FragmentFactory, Fragment>
) : HealthAppContainerFragmentScreen(key, fragmentCreator)