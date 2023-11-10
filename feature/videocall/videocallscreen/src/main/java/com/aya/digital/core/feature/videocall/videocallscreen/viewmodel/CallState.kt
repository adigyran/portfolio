package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import android.os.Parcelable
import com.aya.digital.core.data.base.dataprocessing.dataloading.DataLoadingEffect
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CallState:Parcelable{
    data object Initial:CallState()
    data object Connecting:CallState()
    data object Connected:CallState()

    data object ConnectionFailure:CallState()
    data object Disconnected:CallState()

    data object Reconnecting:CallState()

    data object Reconnected:CallState()


    data object CallEnded:CallState()


    val isConnected: Boolean
        get() = this is Connected || this is Reconnected

    val isConnecting:Boolean
        get() = this is Connecting || this is Reconnecting

    val inActiveCall:Boolean
        get() = isConnected || isConnecting

    val isDisconnected:Boolean
        get() = this is Disconnected || this is ConnectionFailure
}

