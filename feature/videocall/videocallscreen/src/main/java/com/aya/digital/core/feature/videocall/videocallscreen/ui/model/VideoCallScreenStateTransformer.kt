package com.aya.digital.core.feature.videocall.videocallscreen.ui.model

import android.app.PendingIntent
import android.app.RemoteAction
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import com.aya.digital.core.feature.videocall.videocallscreen.ACTION_CALL_CONTROL
import com.aya.digital.core.feature.videocall.videocallscreen.CONTROL_TYPE_TOGGLE_AUDIO
import com.aya.digital.core.feature.videocall.videocallscreen.CONTROL_TYPE_TOGGLE_CAMERA
import com.aya.digital.core.feature.videocall.videocallscreen.EXTRA_CONTROL_TYPE
import com.aya.digital.core.feature.videocall.videocallscreen.R
import com.aya.digital.core.feature.videocall.videocallscreen.REQUEST_DISABLE_AUDIO
import com.aya.digital.core.feature.videocall.videocallscreen.REQUEST_DISABLE_CAMERA
import com.aya.digital.core.feature.videocall.videocallscreen.REQUEST_ENABLE_AUDIO
import com.aya.digital.core.feature.videocall.videocallscreen.REQUEST_ENABLE_CAMERA
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenState
import com.aya.digital.core.mvi.BaseStateTransformer

class VideoCallScreenStateTransformer(private val context: Context) :
    BaseStateTransformer<VideoCallScreenState, VideoCallScreenUiModel>() {
    override fun invoke(state: VideoCallScreenState): VideoCallScreenUiModel =
        VideoCallScreenUiModel(
            roomToken = state.roomToken,
            connectButtonIcn = state.isConnected.getConnectButtonIcn(),
            cameraSwitchVisible = state.localVideoEnabled ,
            localAudioButtonIcn = state.localAudioEnabled.getLocalAudioButtonIcn(),
            localVideoButtonIcn = state.localVideoEnabled.getLocalVideoButtonIcn(),
            localVideoEnabled = state.localVideoEnabled,
            localAudioEnabled = state.localAudioEnabled,
            remoteActions = createRemoteActions(state),
            uiVisibility = !state.pipMode
        )

    private fun Boolean.getConnectButtonIcn() = when (this) {
        true -> R.drawable.ic_call_end_white_24px
        false -> R.drawable.ic_video_call_white_24dp
    }

    private fun Boolean.getLocalAudioButtonIcn() = when (this) {
        true -> R.drawable.ic_mic_white_24dp
        false -> R.drawable.ic_mic_off_black_24dp
    }

    private fun Boolean.getLocalVideoButtonIcn() = when (this) {
        true -> R.drawable.ic_videocam_white_24dp
        false -> R.drawable.ic_videocam_off_black_24dp
    }

    private fun createRemoteActions(state: VideoCallScreenState) = listOf<RemoteAction>(
/*        createRemoteAction(
            state.isConnected.getConnectButtonIcn(),
            REQUEST_END_CALL,
            CONTROL_TYPE_END_CALL
        ),*/
        createRemoteAction(
            state.localVideoEnabled.getLocalVideoButtonIcn(),
            if(state.localVideoEnabled) REQUEST_DISABLE_CAMERA else REQUEST_ENABLE_CAMERA,
            CONTROL_TYPE_TOGGLE_CAMERA
        ),
        createRemoteAction(
            state.localAudioEnabled.getLocalAudioButtonIcn(),
            if(state.localAudioEnabled) REQUEST_DISABLE_AUDIO else REQUEST_ENABLE_AUDIO,
            CONTROL_TYPE_TOGGLE_AUDIO
        ),
    )

    private fun createRemoteAction(
        @DrawableRes iconResId: Int,
        requestCode: Int,
        controlType: Int
    ): RemoteAction {
        return RemoteAction(
            Icon.createWithResource(context, iconResId),
            "",
            "",
            PendingIntent.getBroadcast(
                context,
                requestCode,
                Intent(ACTION_CALL_CONTROL)
                    .putExtra(EXTRA_CONTROL_TYPE, controlType),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}