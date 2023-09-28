package com.aya.digital.core.feature.videocall.videocallscreen.ui.permissions

interface PermissionChecker {
    fun checkPermissionForCameraAndMicrophoneAndBluetooth(): Boolean
    fun requestPermissionForCameraMicrophoneAndBluetooth()
}