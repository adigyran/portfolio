package com.aya.digital.core.mvi

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<STATE : BaseState, SIDE_EFFECT : BaseSideEffect> : ViewModel(),
    ContainerHost<STATE, SIDE_EFFECT>