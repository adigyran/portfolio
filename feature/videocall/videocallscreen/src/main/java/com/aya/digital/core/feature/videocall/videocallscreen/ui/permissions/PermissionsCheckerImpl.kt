package com.aya.digital.core.feature.videocall.videocallscreen.ui.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aya.digital.core.feature.videocall.videocallscreen.R
import timber.log.Timber

class PermissionsCheckerImpl(private val fragment: Fragment, private val onPermissionsGranted:()->Unit) : PermissionChecker {


    override fun checkPermissionForCameraAndMicrophoneAndBluetooth(): Boolean {
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
        return checkPermissions(permissionsList)
    }

    private val requestMultiplePermissions = fragment.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        Timber.d("$permissions")
        var permissionsGranted = false
        for (permission in permissions) {
            permissionsGranted = permission.value
            if (!permissionsGranted) break
        }

        if (permissionsGranted) {
            onPermissionsGranted()
        } else {
            Toast.makeText(
                fragment.requireContext(),
                R.string.permissions_needed,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun requestPermissions(permissions: Array<String>) {
        var displayRational = false
        for (permission in permissions) {
            displayRational =
                displayRational or ActivityCompat.shouldShowRequestPermissionRationale(
                    fragment.requireActivity(),
                    permission
                )
        }
        if (displayRational) {
            val rationalText =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) R.string.permissions_needed_bluetooth else R.string.permissions_needed
            Toast.makeText(
                fragment.requireContext(),
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


    override fun requestPermissionForCameraMicrophoneAndBluetooth() {
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

    private fun checkPermissions(permissions: Array<String>): Boolean {
        var shouldCheck = true
        for (permission in permissions) {
            shouldCheck = shouldCheck and (PackageManager.PERMISSION_GRANTED ==
                    ContextCompat.checkSelfPermission(fragment.requireActivity(), permission))
        }
        return shouldCheck
    }
}

