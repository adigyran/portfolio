package com.aya.digital.core.feature.videocall.videocallscreen.ui.twillioobjects

import com.twilio.video.RemoteAudioTrack
import com.twilio.video.RemoteAudioTrackPublication
import com.twilio.video.RemoteDataTrack
import com.twilio.video.RemoteDataTrackPublication
import com.twilio.video.RemoteParticipant
import com.twilio.video.RemoteVideoTrack
import com.twilio.video.RemoteVideoTrackPublication
import com.twilio.video.Room
import com.twilio.video.TwilioException

class RoomListenerImpl(private val roomListener: RoomListener): Room.Listener {
    override fun onConnected(room: Room) {
        roomListener.onConnected(room)
    }

    override fun onConnectFailure(room: Room, twilioException: TwilioException) {
        roomListener.onConnectFailure(room,twilioException)
    }

    override fun onReconnecting(room: Room, twilioException: TwilioException) {
        
    }

    override fun onReconnected(room: Room) {
    }

    override fun onDisconnected(room: Room, twilioException: TwilioException?) {
        roomListener.onDisconnected(room,twilioException)

    }

    override fun onParticipantConnected(room: Room, remoteParticipant: RemoteParticipant) {
        roomListener.onParticipantConnected(room,remoteParticipant)
    }

    override fun onParticipantDisconnected(room: Room, remoteParticipant: RemoteParticipant) {
        roomListener.onParticipantDisconnected(room,remoteParticipant)
    }

    override fun onRecordingStarted(room: Room) {
        
    }

    override fun onRecordingStopped(room: Room) {
        
    }


}

interface RoomListener{
    fun onConnected(room: Room)
    fun onConnectFailure(room: Room, e: TwilioException)
    fun onDisconnected(room: Room, e: TwilioException?)
    fun onParticipantConnected(room: Room, participant: RemoteParticipant)
    fun onParticipantDisconnected(room: Room, participant: RemoteParticipant)
}