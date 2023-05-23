package com.aya.digital.core.navigation

/**
 * TODO
 *
 */
open class AppFlavour(val flavour:Flavor)

sealed class Flavor
{
    object Patient:Flavor()
    object Doctor:Flavor()
}