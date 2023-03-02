package com.aya.digital.core.ui.base.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.aya.digital.core.ui.base.screens.DiTabContainerFragment

fun hideFragment(fragmentTransaction: FragmentTransaction,fragment : DiTabContainerFragment<*,*,*,*>?)
{
    fragment?.let {
        if(it.isVisible) fragmentTransaction.hide(it).setMaxLifecycle(it,Lifecycle.State.STARTED)
    }
}

fun showFragment(fragmentTransaction: FragmentTransaction,fragment : DiTabContainerFragment<*,*,*,*>?)
{
    fragment?.let {
        when{
            it.isHidden -> fragmentTransaction.show(it).setMaxLifecycle(it,Lifecycle.State.RESUMED)
            else -> it.resetState()
        }

    }
}

fun FragmentManager.getFragmentByTag(tag:String) : DiTabContainerFragment<*,*,*,*>? = this.findFragmentByTag(tag) as? DiTabContainerFragment<*, *, *, *>


fun FragmentTransaction.addFragment(screen: HealthAppTabFragmentScreen,fragmentFactory: FragmentFactory, containerId:Int, tag: String) {
    val fragment = screen.createFragment(fragmentFactory)
    this.add(containerId,fragment,tag)
}

fun checkFragmentNotNull(fragment: DiTabContainerFragment<*, *, *, *>?) = fragment!=null