package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ParticipantState:Parcelable{

    data object ParticipantNotConnected:ParticipantState()

    data object ParticipantConnected:ParticipantState()

    data object ParticipantReconnecting:ParticipantState()

    data object ParticipantReconnected:ParticipantState()


    data object ParticipantDisconnected:ParticipantState()


    val isParticipantConnected: Boolean
        get() = this is ParticipantConnected || this is ParticipantReconnected

    val isParticipantReconnecting:Boolean
        get() = this is ParticipantReconnecting

    val isParticipantDisconnected:Boolean
        get() = this is ParticipantDisconnected
}
