package com.aya.digital.core.feature.tabviews.doctorsearchcontainer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface DoctorSearchScreenTabsScreens {
    fun getFragment(tabId: Int, fm: FragmentManager) : Fragment
}