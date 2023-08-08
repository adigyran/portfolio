package com.aya.digital.core.feature.videocall.videocallscreen.ui

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.videocall.videocallscreen.databinding.ContentVideoBinding
import com.aya.digital.core.feature.videocall.videocallscreen.databinding.ViewVideocallScreenBinding
import com.aya.digital.core.feature.videocall.videocallscreen.di.videoCallScreenDiModule
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenStateTransformer
import com.aya.digital.core.feature.videocall.videocallscreen.ui.model.VideoCallScreenUiModel
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenSideEffects
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenState
import com.aya.digital.core.feature.videocall.videocallscreen.viewmodel.VideoCallScreenViewModel
import com.aya.digital.core.ui.base.screens.DiFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.twilio.audioswitch.AudioDevice
import com.twilio.audioswitch.AudioSwitch
import com.twilio.video.*
import com.twilio.video.ktx.Video
import com.twilio.video.ktx.createLocalAudioTrack
import com.twilio.video.ktx.createLocalVideoTrack
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber
import tvi.webrtc.VideoSink
import kotlin.properties.Delegates
import com.aya.digital.core.feature.videocall.videocallscreen.R as VideocallscreenR

class VideoCallScreenView :
    DiFragment<ViewVideocallScreenBinding, VideoCallScreenViewModel, VideoCallScreenState, VideoCallScreenSideEffects, VideoCallScreenUiModel, VideoCallScreenStateTransformer>() {
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


    private val viewModelFactory: ((Unit) -> VideoCallScreenViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> VideoCallScreenStateTransformer) by kodein.on(
        context = this
    ).factory()

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
    private val roomListener = object : Room.Listener {
        override fun onConnected(room: Room) {
            localParticipant = room.localParticipant
            Timber.d("Connected to ${room.name}")
            contentVideoBinding.videoStatusTextView.text = "connected to room ${room.name}"
            // Only one participant is supported
            room.remoteParticipants.firstOrNull()?.let { addRemoteParticipant(it) }
        }

        override fun onReconnected(room: Room) {
            Timber.d("Connected to ${room.name}")
        }

        override fun onReconnecting(room: Room, twilioException: TwilioException) {
            Timber.d("Reconnecting to ${room.name}")

        }

        override fun onConnectFailure(room: Room, e: TwilioException) {
            Timber.d("Failed to connect")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                    audioSwitch.deactivate()
                }
            } else {
                audioSwitch.deactivate()
            }

            initializeUI()
        }

        override fun onDisconnected(room: Room, e: TwilioException?) {
            localParticipant = null
            this@VideoCallScreenView.room = null
            // Only reinitialize the UI if disconnect was not called from onDestroy()
            if (!disconnectedFromOnDestroy) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (checkPermissionForCameraAndMicrophoneAndBluetooth()) {
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

        override fun onRecordingStarted(room: Room) {
            /*
             * Indicates when media shared to a Room is being recorded. Note that
             * recording is only available in our Group Rooms developer preview.
             */
        }

        override fun onRecordingStopped(room: Room) {
            /*
             * Indicates when media shared to a Room is no longer being recorded. Note that
             * recording is only available in our Group Rooms developer preview.
             */
        }
    }

    /*
     * RemoteParticipant events listener
     */
    private val participantListener = object : RemoteParticipant.Listener {
        override fun onAudioTrackPublished(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication
        ) {

        }

        override fun onAudioTrackUnpublished(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication
        ) {

        }

        override fun onDataTrackPublished(
            remoteParticipant: RemoteParticipant,
            remoteDataTrackPublication: RemoteDataTrackPublication
        ) {

        }

        override fun onDataTrackUnpublished(
            remoteParticipant: RemoteParticipant,
            remoteDataTrackPublication: RemoteDataTrackPublication
        ) {

        }

        override fun onVideoTrackPublished(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication
        ) {

        }

        override fun onVideoTrackUnpublished(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication
        ) {

        }

        override fun onAudioTrackSubscribed(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication,
            remoteAudioTrack: RemoteAudioTrack
        ) {

        }

        override fun onAudioTrackUnsubscribed(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication,
            remoteAudioTrack: RemoteAudioTrack
        ) {

        }

        override fun onAudioTrackSubscriptionFailed(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication,
            twilioException: TwilioException
        ) {

        }

        override fun onDataTrackSubscribed(
            remoteParticipant: RemoteParticipant,
            remoteDataTrackPublication: RemoteDataTrackPublication,
            remoteDataTrack: RemoteDataTrack
        ) {

        }

        override fun onDataTrackUnsubscribed(
            remoteParticipant: RemoteParticipant,
            remoteDataTrackPublication: RemoteDataTrackPublication,
            remoteDataTrack: RemoteDataTrack
        ) {

        }

        override fun onDataTrackSubscriptionFailed(
            remoteParticipant: RemoteParticipant,
            remoteDataTrackPublication: RemoteDataTrackPublication,
            twilioException: TwilioException
        ) {
        }

        override fun onVideoTrackSubscribed(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication,
            remoteVideoTrack: RemoteVideoTrack
        ) {
            addRemoteParticipantVideo(remoteVideoTrack)
        }

        override fun onVideoTrackUnsubscribed(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication,
            remoteVideoTrack: RemoteVideoTrack
        ) {

        }

        override fun onVideoTrackSubscriptionFailed(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication,
            twilioException: TwilioException
        ) {
            Timber.d("onVideoTrackSubscriptionFailed ${twilioException.message}")
        }

        override fun onAudioTrackEnabled(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication
        ) {
        }

        override fun onVideoTrackEnabled(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication
        ) {
        }

        override fun onVideoTrackDisabled(
            remoteParticipant: RemoteParticipant,
            remoteVideoTrackPublication: RemoteVideoTrackPublication
        ) {
        }

        override fun onAudioTrackDisabled(
            remoteParticipant: RemoteParticipant,
            remoteAudioTrackPublication: RemoteAudioTrackPublication
        ) {
        }
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

        localVideoView = contentVideoBinding.primaryVideoView

        savedVolumeControlStream = requireActivity().volumeControlStream
        requireActivity().volumeControlStream = AudioManager.STREAM_VOICE_CALL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                requestPermissionForCameraMicrophoneAndBluetooth()
            } else {
                createAudioAndVideoTracks()
                audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }
            }
        } else if (!checkPermissionForCameraAndMicrophone()) {
            requestPermissionForCameraMicrophoneAndBluetooth()
        } else {
            createAudioAndVideoTracks()
            audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }
        }
        initializeUI()
    }

    private fun initializeUI() {
        binding.connectActionFab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                VideocallscreenR.drawable.ic_video_call_white_24dp
            )
        )
        contentVideoBinding.videoStatusTextView.text = "not connected, room ${param.roomId}"
        binding.switchAudioDeviceActionFab.show()
        binding.switchAudioDeviceActionFab.setOnClickListener(switchAudioDeviceClickListener())
        binding.connectActionFab.show()
        binding.connectActionFab.setOnClickListener(connectActionClickListener())
        binding.switchCameraActionFab.show()
        binding.switchCameraActionFab.setOnClickListener(switchCameraClickListener())
        binding.localVideoActionFab.show()
        binding.localVideoActionFab.setOnClickListener(localVideoClickListener())
        binding.muteActionFab.show()
        binding.muteActionFab.setOnClickListener(muteClickListener())
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
        }
    }


    private val requestMultiplePermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        Timber.d("$permissions")
        var permissionsGranted = false
        for (permission in permissions)
        {
            permissionsGranted = permission.value
            if (!permissionsGranted) break
        }

        if (permissionsGranted) {
            Timber.d("permission granted")
            audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }
            createAudioAndVideoTracks()
        } else {
            Toast.makeText(
                requireContext(),
                VideocallscreenR.string.permissions_needed,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    /*  override fun onRequestPermissionsResult(
          requestCode: Int,
          permissions: Array<String>,
          grantResults: IntArray
      ) {
          Timber.d("$requestCode $permissions $grantResults")
          if (requestCode == CAMERA_MIC_PERMISSION_REQUEST_CODE) {
              *//*
             * The first two permissions are Camera & Microphone, bluetooth isn't required but
             * enabling it enables bluetooth audio routing functionality.
             *//*
            val cameraAndMicPermissionGranted =
                ((PackageManager.PERMISSION_GRANTED == grantResults[CAMERA_PERMISSION_INDEX])
                        and (PackageManager.PERMISSION_GRANTED == grantResults[MIC_PERMISSION_INDEX]))

            *//*
             * Due to bluetooth permissions being requested at the same time as camera and mic
             * permissions, AudioSwitch should be started after providing the user the option
             * to grant the necessary permissions for bluetooth.
             *//*
            // audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }

            if (cameraAndMicPermissionGranted) {
                Timber.d("permission granted")
                createAudioAndVideoTracks()
            } else {
                Toast.makeText(
                    requireContext(),
                    VideocallscreenR.string.permissions_needed,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }*/

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
        viewModel.disconnectClicked()
    }

    private fun checkPermissions(permissions: Array<String>): Boolean {
        var shouldCheck = true
        for (permission in permissions) {
            shouldCheck = shouldCheck and (PackageManager.PERMISSION_GRANTED ==
                    ContextCompat.checkSelfPermission(requireActivity(), permission))
        }
        return shouldCheck
    }

    private fun requestPermissions(permissions: Array<String>) {
        var displayRational = false
        for (permission in permissions) {
            displayRational =
                displayRational or ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    permission
                )
        }
        if (displayRational) {
            val rationalText =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) VideocallscreenR.string.permissions_needed_bluetooth else VideocallscreenR.string.permissions_needed
            Toast.makeText(
                requireContext(),
                rationalText,
                Toast.LENGTH_LONG
            ).show()
        } else {
            Timber.d("$permissions")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                requestMultiplePermissions.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.BLUETOOTH_CONNECT
                    )
                )
            } else {
                requestMultiplePermissions.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                    )
                )
            }
        }
    }

    private fun checkPermissionForCameraAndMicrophone(): Boolean {
        return checkPermissions(
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
        )
    }

    private fun checkPermissionForCameraAndMicrophoneAndBluetooth(): Boolean {
        return checkPermissions(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        )
    }

    private fun requestPermissionForCameraMicrophoneAndBluetooth() {
        val permissionsList: Array<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )
        }
        requestPermissions(permissionsList)
    }

    private fun createAudioAndVideoTracks() {
        Timber.d("create tracks")
        // Share your microphone
        localAudioTrack = createLocalAudioTrack(requireActivity(), true)

        // Share your camera
        localVideoTrack = createLocalVideoTrack(
            requireActivity(),
            true,
            cameraCapturerCompat
        )
    }

    private fun connectToRoom(roomName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!checkPermissionForCameraAndMicrophoneAndBluetooth()) {
                requestPermissionForCameraMicrophoneAndBluetooth()
                return
            }
        }
        if (!checkPermissionForCameraAndMicrophone()) {
            requestPermissionForCameraMicrophoneAndBluetooth()
            return
        }
        setDisconnectAction()
        audioSwitch.activate()
        room = Video.connect(requireActivity(), accessToken, roomListener) {
            roomName(roomName)
            /*
             * Add local audio track to connect options to share with participants.
             */
            audioTracks(listOf(localAudioTrack))
            /*
             * Add local video track to connect options to share with participants.
             */
            videoTracks(listOf(localVideoTrack))

            /*
             * Set the preferred audio and video codec for media.
             */
            preferAudioCodecs(listOf(audioCodec))
            preferVideoCodecs(listOf(videoCodec))

            /*
             * Set the sender side encoding parameters.
             */
            encodingParameters(encodingParameters)

            /*
             * Toggles automatic track subscription. If set to false, the LocalParticipant will receive
             * notifications of track publish events, but will not automatically subscribe to them. If
             * set to true, the LocalParticipant will automatically subscribe to tracks as they are
             * published. If unset, the default is true. Note: This feature is only available for Group
             * Rooms. Toggling the flag in a P2P room does not modify subscription behavior.
             */
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

    /*
     * Update the menu icon based on the currently selected audio device.
     */
    private fun updateAudioDeviceIcon(selectedAudioDevice: AudioDevice?) {
        val audioDeviceMenuIcon = when (selectedAudioDevice) {
            is AudioDevice.BluetoothHeadset -> VideocallscreenR.drawable.ic_bluetooth_white_24dp
            is AudioDevice.WiredHeadset -> VideocallscreenR.drawable.ic_headset_mic_white_24dp
            is AudioDevice.Speakerphone -> VideocallscreenR.drawable.ic_volume_up_white_24dp
            else -> VideocallscreenR.drawable.ic_phonelink_ring_white_24dp
        }

        audioDeviceMenuItem?.setIcon(audioDeviceMenuIcon)
    }

    /*
     * The actions performed during disconnect.
     */
    private fun setDisconnectAction() {
        binding.connectActionFab.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                VideocallscreenR.drawable.ic_call_end_white_24px
            )
        )
        binding.connectActionFab.show()
        binding.connectActionFab.setOnClickListener(disconnectClickListener())
    }

    /*
     * Creates an connect UI dialog
     */
    private fun showConnectDialog() {
        /*  alertDialog = createConnectDialog(
              roomEditText,
              connectClickListener(roomEditText), cancelConnectDialogClickListener(), this
          )
          alertDialog?.show()*/
    }

    /*
     * Called when participant joins the room
     */
    private fun addRemoteParticipant(remoteParticipant: RemoteParticipant) {
        /*
         * This app only displays video for one additional participant per Room
         */
        Timber.d("addRemoteParticipant $remoteParticipant")
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

        /*
         * Add participant renderer
         */
        remoteParticipant.remoteVideoTracks.firstOrNull()?.let { remoteVideoTrackPublication ->
            Timber.d("addRemoteParticipant ${remoteVideoTrackPublication.isTrackSubscribed} $remoteVideoTrackPublication")
            if (remoteVideoTrackPublication.isTrackSubscribed) {
                remoteVideoTrackPublication.remoteVideoTrack?.let { addRemoteParticipantVideo(it) }
            }
        }

        /*
         * Start listening for participant events
         */
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

    private fun connectClickListener(roomEditText: EditText): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { _, _ ->
            /*
             * Connect to room
             */
            connectToRoom(roomEditText.text.toString())
        }
    }


    private fun connectActionClickListener(): View.OnClickListener {
        return View.OnClickListener {
            contentVideoBinding.videoStatusTextView.text = "Connecting to room ${param.roomId}"
            //setDisconnectAction()
            viewModel.connectClicked()
        }
    }

    private fun cancelConnectDialogClickListener(): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { _, _ ->
        }
    }

    private fun localVideoClickListener(): View.OnClickListener {
        return View.OnClickListener {
            /*
             * Enable/disable the local video track
             */
            localVideoTrack?.let {
                val enable = !it.isEnabled
                it.enable(enable)
                val icon: Int
                if (enable) {
                    icon = VideocallscreenR.drawable.ic_videocam_white_24dp
                    binding.switchCameraActionFab.show()
                } else {
                    icon = VideocallscreenR.drawable.ic_videocam_off_black_24dp
                    binding.switchCameraActionFab.hide()
                }
                binding.localVideoActionFab.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), icon)
                )
            }
        }
    }


    private fun switchAudioDeviceClickListener(): View.OnClickListener {
        return View.OnClickListener {

            showAudioDevices()
        }
    }

    private fun muteClickListener(): View.OnClickListener {
        return View.OnClickListener {
            /*
             * Enable/disable the local audio track. The results of this operation are
             * signaled to other Participants in the same Room. When an audio track is
             * disabled, the audio is muted.
             */
            localAudioTrack?.let {
                val enable = !it.isEnabled
                it.enable(enable)
                val icon = if (enable)
                    VideocallscreenR.drawable.ic_mic_white_24dp
                else
                    VideocallscreenR.drawable.ic_mic_off_black_24dp
                binding.muteActionFab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), icon
                    )
                )
            }
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

    private fun disconnectClickListener(): View.OnClickListener {
        return View.OnClickListener {
            /*
             * Disconnect from room
             */
            showDisconnectActionsDialog()
        }
    }


    override fun onResume() {
        super.onResume()
        /*
         * If the local video track was released when the app was put in the background, recreate.
         */
        localVideoTrack = if (localVideoTrack == null && checkPermissionForCameraAndMicrophone()) {
            createLocalVideoTrack(
                requireActivity(),
                true,
                cameraCapturerCompat
            )
        } else {
            localVideoTrack
        }
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

    override fun onPause() {
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
        super.onPause()
    }

    override fun onDestroy() {
        /*
         * Tear down audio management and restore previous volume stream
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (checkPermissionForCameraAndMicrophoneAndBluetooth()) {
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
        room?.disconnect()
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
}
