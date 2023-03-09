package com.aya.digital.feature.auth.chooser.viewmodel

import android.os.Parcelable
import com.aya.digital.core.mvi.BaseState
import com.aya.digital.core.ui.adapters.base.DiffItem
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class AuthChooserState(val stub: Boolean = false) : BaseState
