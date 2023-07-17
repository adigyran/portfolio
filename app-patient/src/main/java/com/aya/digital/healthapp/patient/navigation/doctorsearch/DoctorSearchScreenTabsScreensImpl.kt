package com.aya.digital.healthapp.patient.navigation.doctorsearch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapScreen
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation.DoctorSearchListScreen
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.DoctorSearchScreenTabsScreens

class DoctorSearchScreenTabsScreensImpl:DoctorSearchScreenTabsScreens {
    override fun getFragment(tabId: Int, fm: FragmentManager): Fragment = when(tabId)
    {
        0 -> {DoctorSearchListScreen.createFragment(fm.fragmentFactory)}
        1 -> {
            DoctorSearchMapScreen.createFragment(fm.fragmentFactory)}
        else -> {DoctorSearchListScreen.createFragment(fm.fragmentFactory)}
    }

}