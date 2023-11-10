package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import android.content.Context
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.CallState
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.ParticipantState

class StatusesTextsManager(private val context: Context) {

    fun getCallStateText(callState: CallState):String =
        when(callState)
        {
            CallState.CallEnded -> "call ended"
            CallState.Connected -> "connected to the room"
            CallState.Connecting -> "connecting to room"
            CallState.ConnectionFailure -> "failure to connect"
            CallState.Disconnected -> "disconnected from the room"
            CallState.Initial -> "press call button to connect"
            CallState.Reconnected -> "returned to the room"
            CallState.Reconnecting -> "lost connection to room, reconnecting"
        }

    fun getParticipantStateText(participantState: ParticipantState,participantName:String): String {

        val stateText = when(participantState)
        {
            ParticipantState.ParticipantConnected -> "connected"
            ParticipantState.ParticipantDisconnected -> "disconnected"
            ParticipantState.ParticipantNotConnected -> "not connected"
            ParticipantState.ParticipantReconnected -> "reconnected"
            ParticipantState.ParticipantReconnecting -> "lost connection, reconnecting"
        }

        return "%s: %s".format(participantName,stateText)
    }

    private fun getStringFromRes(resId:Int) {

    }
}
