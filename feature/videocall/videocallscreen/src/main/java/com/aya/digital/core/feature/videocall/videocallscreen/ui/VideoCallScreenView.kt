package com.aya.digital.core.feature.videocall.videocallscreen.ui

import android.Manifest
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Rational
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.registerReceiver
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.ext.booleans
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.ext.toggleVisibility
import com.aya.digital.core.feature.videocall.videocallscreen.ACTION_CALL_CONTROL
import com.aya.digital.core.feature.videocall.videocallscreen.CONTROL_TYPE_END_CALL
import com.aya.digital.core.feature.videocall.videocallscreen.CONTROL_TYPE_TOGGLE_AUDIO
import com.aya.digital.core.feature.videocall.videocallscreen.CONTROL_TYPE_TOGGLE_CAMERA
import com.aya.digital.core.feature.videocall.videocallscreen.EXTRA_CONTROL_TYPE
import com.aya.digital.core.feature.videocall.videocallscreen.databinding.ContentVideoBinding
import com.aya.digital.core.feature.videocall.videocallscreen.databinding.ViewVideocallScreenBinding
import com.aya.digital.core.feature.videocall.videocallscreen.di.videoCallScreenDiModule
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenStateTransformer
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenUiModel
import com.aya.digital.core.feature.videocall.videocallscreen.ui.permissions.PermissionChecker
import com.aya.digital.core.feature.videocall.videocallscreen.ui.permissions.PermissionsCheckerImpl
import com.aya.digital.core.feature.videocall.videocallscreen.ui.twillioobjects.CameraCapturerCompat
import com.aya.digital.core.feature.videocall.videocallscreen.ui.twillioobjects.RemoteParticipantListener
import com.aya.digital.core.feature.videocall.videocallscreen.ui.twillioobjects.RemoteParticipantListenerImpl
import com.aya.digital.core.feature.videocall.videocallscreen.ui.twillioobjects.RoomListener
import com.aya.digital.core.feature.videocall.videocallscreen.ui.twillioobjects.RoomListenerImpl
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenSideEffects
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenState
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenViewModel
import com.aya.digital.core.navigation.utils.BackButtonListener
import com.aya.digital.core.ui.base.screens.DiFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.twilio.audioswitch.AudioDevice
import com.twilio.audioswitch.AudioSwitch
import com.twilio.video.*
import com.twilio.video.ktx.Video
import com.twilio.video.ktx.createLocalAudioTrack
import com.twilio.video.ktx.createLocalVideoTrack
import com.twilio.video.ktx.enabled
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber
import tvi.webrtc.VideoSink
import kotlin.properties.Delegates
import com.aya.digital.core.feature.videocall.videocallscreen.R as VideocallscreenR




class VideoCallScreenView :
    DiFragment<ViewVideocallScreenBinding, VideoCallScreenViewModel, VideoCallScreenState, VideoCallScreenSideEffects, VideoCallScreenUiModel, VideoCallScreenStateTransformer>(), BackButtonListener {
    private val CAMERA_MIC_PERMISSION_REQUEST_CODE = 1
    private val CAMERA_PERMISSION_INDEX = 0
    private val MIC_PERMISSION_INDEX = 1


    private var param: Param by argument("param")

    private val TWILIO_ACCESS_TOKEN = "BuildConfig.TWILIO_ACCESS_TOKEN"
    private val ACCESS_TOKEN_SERVER = "BuildConfig.TWILIO_ACCESS_TOKEN_SERVER"

    /*
     * Access token used to connect. This field will be set either from the console generated token
     * or the request to the token server.
     */
    private lateinit var accessToken: String

    private var room: Room? = null
    private var localParticipant: LocalParticipant? = null

    private val localVideoTrackReadySubject =
        BehaviorSubject.create<Boolean>().apply { onNext(false) }
    private val localAudioTrackReadySubject =
        BehaviorSubject.create<Boolean>().apply { onNext(false) }


    private val viewModelFactory: ((Unit) -> VideoCallScreenViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> VideoCallScreenStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val permissionChecker: PermissionChecker by lazy {
        PermissionsCheckerImpl(fragment = this) {
            audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }
            createAudioAndVideoTracks()
        }
    }

    private val audioCodec: AudioCodec
        get() {
            return OpusCodec()
        }
    private val videoCodec: VideoCodec
        get() {
            return Vp8Codec()
        }

    private val enableAutomaticSubscription: Boolean
        get() {
            return true
        }
    private val encodingParameters: EncodingParameters
        get() {

            val maxAudioBitrate = 0

            val maxVideoBitrate = 0

            return EncodingParameters(maxAudioBitrate, maxVideoBitrate)
        }


    /*
 * Room events listener
 */
    private val broadcastReceiver = object : BroadcastReceiver() {

        // Called when an item is clicked.
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null || intent.action != ACTION_CALL_CONTROL) {
                return
            }
            when (intent.getIntExtra(EXTRA_CONTROL_TYPE, 0)) {
                CONTROL_TYPE_END_CALL -> viewModel.toggleConnectionClicked()
                CONTROL_TYPE_TOGGLE_CAMERA -> viewModel.toggleLocalVideoClicked()
                CONTROL_TYPE_TOGGLE_AUDIO -> viewModel.toggleLocalAudioClicked()
            }
        }
    }
    private val roomListener = RoomListenerImpl(object : RoomListener {
        override fun onConnected(room: Room) {
            viewModel.onSuccessfulConnection()
            localParticipant = room.localParticipant
            contentVideoBinding.videoStatusTextView.text = "connected to room ${room.name}"
            // Only one participant is supported
            room.remoteParticipants.firstOrNull()?.let { addRemoteParticipant(it) }
        }


        override fun onConnectFailure(room: Room, e: TwilioException) {
            viewModel.onConnectionFailure()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (permissionChecker.checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                    audioSwitch.deactivate()
                }
            } else {
                audioSwitch.deactivate()
            }
        }

        override fun onDisconnected(room: Room, e: TwilioException?) {
            localParticipant = null
            this@VideoCallScreenView.room = null
            // Only reinitialize the UI if disconnect was not called from onDestroy()
            if (!disconnectedFromOnDestroy) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (permissionChecker.checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                        audioSwitch.deactivate()
                    }
                } else {
                    audioSwitch.deactivate()
                }
            }
        }

        override fun onParticipantConnected(room: Room, participant: RemoteParticipant) {
            addRemoteParticipant(participant)
        }

        override fun onParticipantDisconnected(room: Room, participant: RemoteParticipant) {
            removeRemoteParticipant(participant)
        }

    })

    private val participantListener by lazy {
        RemoteParticipantListenerImpl(object : RemoteParticipantListener {
            override fun onVideoTrackSubscribed(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication,
                remoteVideoTrack: RemoteVideoTrack
            ) {
                addRemoteParticipantVideo(remoteVideoTrack)
            }
        })
    }

    private var localAudioTrack: LocalAudioTrack? = null
    private var localVideoTrack: LocalVideoTrack? = null
    private val cameraCapturerCompat by lazy {
        CameraCapturerCompat(requireActivity(), CameraCapturerCompat.Source.FRONT_CAMERA)
    }

    private val audioSwitch by lazy {
        AudioSwitch(
            getAppInstance(), preferredDeviceList = listOf(
                AudioDevice.BluetoothHeadset::class.java,
                AudioDevice.WiredHeadset::class.java,
                AudioDevice.Speakerphone::class.java,
                AudioDevice.Earpiece::class.java
            )
        )
    }
    private var savedVolumeControlStream by Delegates.notNull<Int>()
    private var audioDeviceMenuItem: MenuItem? = null

    private var participantIdentity: String? = null
    private lateinit var localVideoView: VideoSink
    private var disconnectedFromOnDestroy = false
    private var isSpeakerPhoneEnabled = true

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)

    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        localAudioTrackReadySubject.onNext(false)
        localVideoTrackReadySubject.onNext(false)
        localVideoView = contentVideoBinding.primaryVideoView

        savedVolumeControlStream = requireActivity().volumeControlStream
        requireActivity().volumeControlStream = AudioManager.STREAM_VOICE_CALL
        if (!permissionChecker.checkPermissionForCameraAndMicrophoneAndBluetooth()) {
            permissionChecker.requestPermissionForCameraMicrophoneAndBluetooth()
        } else {
            createAudioAndVideoTracks()
            audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }
        }

        initializeUI()
        viewModel.resumeOngoingConnection()

        requireActivity().registerReceiver(broadcastReceiver, IntentFilter(ACTION_CALL_CONTROL))

    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
        viewModel.togglePipMode(isInPictureInPictureMode)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun updatePictureInPictureParams(remoteActions:List<RemoteAction>): PictureInPictureParams {
        val params = PictureInPictureParams.Builder()
            .setActions(
                remoteActions
            )

            .setAutoEnterEnabled(true)
            .setSeamlessResizeEnabled(false)
            .setAspectRatio(Rational(16, 9))
            .build()
        requireActivity().setPictureInPictureParams(params)
        return params
    }

    private fun initializeUI() {
        contentVideoBinding.videoStatusTextView.text = "not connected, room ${param.roomId}"
        binding.switchAudioDeviceActionFab.setOnClickListener(switchAudioDeviceClickListener())
        binding.connectActionFab.show()
        binding.connectActionFab bindClick { viewModel.toggleConnectionClicked() }
        binding.switchCameraActionFab.setOnClickListener(switchCameraClickListener())
        binding.localVideoActionFab.show()
        binding.localVideoActionFab bindClick { viewModel.toggleLocalVideoClicked() }
        binding.muteActionFab.show()
        binding.muteActionFab bindClick { viewModel.toggleLocalAudioClicked() }
    }

    override fun sideEffect(sideEffect: VideoCallScreenSideEffects) {
        when (sideEffect) {
            is VideoCallScreenSideEffects.Error -> {
                processErrorSideEffect(sideEffect.error)
            }

            is VideoCallScreenSideEffects.ConnectToRoom -> {
                this@VideoCallScreenView.accessToken = sideEffect.roomToken
                contentVideoBinding.videoStatusTextView.text = "Connecting to room ${param.roomId}"
                connectToRoom(sideEffect.roomId)
            }

            VideoCallScreenSideEffects.ShowDisconnectDialog -> {
                showDisconnectActionsDialog()
            }
        }
    }

    override fun provideDiModule(): DI.Module =
        videoCallScreenDiModule(tryTyGetParentRouter(), param)

    private lateinit var contentVideoBinding: ContentVideoBinding
    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewVideocallScreenBinding {

        val viewVideocallScreenBinding =
            ViewVideocallScreenBinding.inflate(inflater, container, false)
        contentVideoBinding = ContentVideoBinding.bind(viewVideocallScreenBinding.root)
        return viewVideocallScreenBinding
    }

    override fun render(state: VideoCallScreenState) {
        stateTransformer(state).run {
            this.roomToken?.let { accessToken ->

            }
            this.connectButtonIcn.let { icon -> binding.connectActionFab.setButtonIcon(icon) }
            this.localVideoButtonIcn.let { icon -> binding.localVideoActionFab.setButtonIcon(icon) }
            this.localAudioButtonIcn.let { icon -> binding.muteActionFab.setButtonIcon(icon) }
            this.cameraSwitchVisible.let { binding.switchCameraActionFab.toggleVisibility(it) }
            this.localAudioEnabled.let { audioEnabled ->
                localAudioTrackReadySubject.subscribe {
                    if (!it) return@subscribe
                    localAudioTrack?.apply { enabled = audioEnabled }
                }

            }
            this.localVideoEnabled.let { videoEnabled ->
                localVideoTrackReadySubject.subscribe {
                    if (!it) return@subscribe
                    localVideoTrack?.apply { enabled = videoEnabled }
                }
            }
            this.remoteActions.let { remoteActions ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    updatePictureInPictureParams(remoteActions
                    )
                }
            }
            this.uiVisibility.let {
                binding.buttonsContainer.toggleVisibility(it)
                contentVideoBinding.videoStatusTextView.toggleVisibility(it)
            }

        }
    }

    private fun Int.getButtonIcon() = ContextCompat.getDrawable(requireContext(), this)

    private fun FloatingActionButton.setButtonIcon(icon: Int) =
        setImageDrawable(icon.getButtonIcon())


    private fun showDisconnectActionsDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Disconnect?")
            .setMessage("Are you sure you want to disconnect?")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Disconnect") { dialog, which ->
                disconnect()
                dialog.dismiss()
            }
            .show()
    }

    private fun disconnect() {
        room?.disconnect()
        viewModel.onDisconnectConfirmed()
    }

    private fun createAudioAndVideoTracks() {
        Timber.d("create tracks")
        // Share your microphone
        localAudioTrack = createLocalAudioTrack(requireActivity(), true)
        localAudioTrackReadySubject.onNext(true)
        // Share your camera
        localVideoTrack = createLocalVideoTrack(
            requireActivity(),
            true,
            cameraCapturerCompat
        )
        localVideoTrackReadySubject.onNext(true)
    }

    private fun connectToRoom(roomName: String) {
        if (!permissionChecker.checkPermissionForCameraAndMicrophoneAndBluetooth()) {
            permissionChecker.requestPermissionForCameraMicrophoneAndBluetooth()
            return
        }
        audioSwitch.activate()
        room = Video.connect(requireActivity(), accessToken, roomListener) {
            roomName(roomName)
            audioTracks(listOf(localAudioTrack))
            videoTracks(listOf(localVideoTrack))
            preferAudioCodecs(listOf(audioCodec))
            preferVideoCodecs(listOf(videoCodec))
            encodingParameters(encodingParameters)
            enableAutomaticSubscription(enableAutomaticSubscription)
        }
    }

    private fun showAudioDevices() {
        val availableAudioDevices = audioSwitch.availableAudioDevices

        audioSwitch.selectedAudioDevice?.let { selectedDevice ->
            val selectedDeviceIndex = availableAudioDevices.indexOf(selectedDevice)
            val audioDeviceNames = ArrayList<String>()

            for (a in availableAudioDevices) {
                audioDeviceNames.add(a.name)
            }

            AlertDialog.Builder(requireActivity())
                .setTitle(VideocallscreenR.string.room_screen_select_device)
                .setSingleChoiceItems(
                    audioDeviceNames.toTypedArray<CharSequence>(),
                    selectedDeviceIndex
                ) { dialog, index ->
                    dialog.dismiss()
                    val selectedAudioDevice = availableAudioDevices[index]
                    updateAudioDeviceIcon(selectedAudioDevice)
                    audioSwitch.selectDevice(selectedAudioDevice)
                }.create().show()
        }
    }

    private fun updateAudioDeviceIcon(selectedAudioDevice: AudioDevice?) {
        val audioDeviceMenuIcon = when (selectedAudioDevice) {
            is AudioDevice.BluetoothHeadset -> VideocallscreenR.drawable.ic_bluetooth_white_24dp
            is AudioDevice.WiredHeadset -> VideocallscreenR.drawable.ic_headset_mic_white_24dp
            is AudioDevice.Speakerphone -> VideocallscreenR.drawable.ic_volume_up_white_24dp
            else -> VideocallscreenR.drawable.ic_phonelink_ring_white_24dp
        }

        audioDeviceMenuItem?.setIcon(audioDeviceMenuIcon)
    }

    private fun addRemoteParticipant(remoteParticipant: RemoteParticipant) {
        if (contentVideoBinding.thumbnailVideoView.visibility == View.VISIBLE) {
            Snackbar.make(
                binding.connectActionFab,
                "Multiple participants are not currently support in this UI",
                Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
            return
        }
        participantIdentity = remoteParticipant.identity
        contentVideoBinding.videoStatusTextView.text = "Participant $participantIdentity joined"
        remoteParticipant.remoteVideoTracks.firstOrNull()?.let { remoteVideoTrackPublication ->
            if (remoteVideoTrackPublication.isTrackSubscribed) {
                remoteVideoTrackPublication.remoteVideoTrack?.let { addRemoteParticipantVideo(it) }
            }
        }
        remoteParticipant.setListener(participantListener)
    }

    /*
     * Set primary view as renderer for participant video track
     */
    private fun addRemoteParticipantVideo(videoTrack: VideoTrack) {
        Timber.d("addRemoteParticipantVideo $videoTrack")
        moveLocalVideoTothumbnailView()
        contentVideoBinding.primaryVideoView.mirror = false
        videoTrack.addSink(contentVideoBinding.primaryVideoView)
    }

    private fun moveLocalVideoTothumbnailView() {
        if (contentVideoBinding.thumbnailVideoView.visibility == View.GONE) {
            contentVideoBinding.thumbnailVideoView.visibility = View.VISIBLE
            with(localVideoTrack) {
                this?.removeSink(contentVideoBinding.primaryVideoView)
                this?.addSink(contentVideoBinding.thumbnailVideoView)
            }
            localVideoView = contentVideoBinding.thumbnailVideoView
            contentVideoBinding.thumbnailVideoView.mirror = cameraCapturerCompat.cameraSource ==
                    CameraCapturerCompat.Source.FRONT_CAMERA
        }
    }

    /*
     * Called when participant leaves the room
     */
    private fun removeRemoteParticipant(remoteParticipant: RemoteParticipant) {
        contentVideoBinding.videoStatusTextView.text =
            "Participant $remoteParticipant.identity left."
        if (remoteParticipant.identity != participantIdentity) {
            return
        }

        /*
         * Remove participant renderer
         */
        remoteParticipant.remoteVideoTracks.firstOrNull()?.let { remoteVideoTrackPublication ->
            if (remoteVideoTrackPublication.isTrackSubscribed) {
                remoteVideoTrackPublication.remoteVideoTrack?.let { removeParticipantVideo(it) }
            }
        }
        moveLocalVideoToprimaryView()
    }

    private fun removeParticipantVideo(videoTrack: VideoTrack) {
        videoTrack.removeSink(contentVideoBinding.primaryVideoView)
    }

    private fun moveLocalVideoToprimaryView() {
        if (contentVideoBinding.thumbnailVideoView.visibility == View.VISIBLE) {
            contentVideoBinding.thumbnailVideoView.visibility = View.GONE
            with(localVideoTrack) {
                this?.removeSink(contentVideoBinding.thumbnailVideoView)
                this?.addSink(contentVideoBinding.primaryVideoView)
            }
            localVideoView = contentVideoBinding.primaryVideoView
            contentVideoBinding.primaryVideoView.mirror = cameraCapturerCompat.cameraSource ==
                    CameraCapturerCompat.Source.FRONT_CAMERA
        }
    }


    private fun switchAudioDeviceClickListener(): View.OnClickListener {
        return View.OnClickListener {

            showAudioDevices()
        }
    }


    private fun switchCameraClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val cameraSource = cameraCapturerCompat.cameraSource
            cameraCapturerCompat.switchCamera()
            if (contentVideoBinding.thumbnailVideoView.visibility == View.VISIBLE) {
                contentVideoBinding.thumbnailVideoView.mirror =
                    cameraSource == CameraCapturerCompat.Source.BACK_CAMERA
            } else {
                contentVideoBinding.primaryVideoView.mirror =
                    cameraSource == CameraCapturerCompat.Source.BACK_CAMERA
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (requireActivity().isInPictureInPictureMode) {

        } else {
            /*
         * If the local video track was released when the app was put in the background, recreate.
         */
            localVideoTrack =
                if (localVideoTrack == null && permissionChecker.checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                    createLocalVideoTrack(
                        requireActivity(),
                        true,
                        cameraCapturerCompat
                    )

                } else {
                    localVideoTrack
                }
            localVideoTrackReadySubject.onNext(true)
            localVideoTrack?.addSink(localVideoView)

            /*
         * If connected to a Room then share the local video track.
         */
            localVideoTrack?.let { localParticipant?.publishTrack(it) }

            /*
         * Update encoding parameters if they have changed.
         */
            localParticipant?.setEncodingParameters(encodingParameters)

            /*
         * Update reconnecting UI
         */
            room?.let {
                /*  reconnectingProgressBar.visibility = if (it.state != Room.State.RECONNECTING)
                  View.GONE else
                  View.VISIBLE
              videoStatusTextView.text = "Connected to ${it.name}"*/
            }
        }
    }


    override fun onPause() {
        if (requireActivity().isInPictureInPictureMode) {

        } else {
            /*
     * If this local video track is being shared in a Room, remove from local
     * participant before releasing the video track. Participants will be notified that
     * the track has been removed.
     */
            localVideoTrack?.let { localParticipant?.unpublishTrack(it) }

            /*
             * Release the local video track before going in the background. This ensures that the
             * camera can be used by other applications while this app is in the background.
             */
            localVideoTrack?.release()
            localVideoTrack = null
            localVideoTrackReadySubject.onNext(false)
        }
        super.onPause()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Timber.d("CHANGED")
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        Timber.d("Destroyed")
        /*
         * Tear down audio management and restore previous volume stream
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (permissionChecker.checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                audioSwitch.stop()
            }
        } else {
            audioSwitch.stop()
        }
        requireActivity().volumeControlStream = savedVolumeControlStream

        /*
         * Always disconnect from the room before leaving the Activity to
         * ensure any memory allocated to the Room resource is freed.
         */
        //room?.disconnect()
        disconnectedFromOnDestroy = true

        /*
         * Release the local audio and video tracks ensuring any memory allocated to audio
         * or video is freed.
         */
        localAudioTrack?.release()
        localVideoTrack?.release()

        super.onDestroy()
    }

    @Parcelize
    class Param(
        val roomId: Int
    ) : Parcelable

    override fun provideViewModel(): VideoCallScreenViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): VideoCallScreenStateTransformer =
        stateTransformerFactory(Unit)




    companion object {
        fun getNewInstance(roomId: Int): VideoCallScreenView =
            createFragment(
                Param(roomId)
            )
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBack()
        return true
    }
}
