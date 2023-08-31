package com.aya.digital.core.feature.tabviews.home.viewmodel

import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeState(val name:String,val lastUpdates:HashMap<String,LastUpdatesModel>?=null) : BaseState
