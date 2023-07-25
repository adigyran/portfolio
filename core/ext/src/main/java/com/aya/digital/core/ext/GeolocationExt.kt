package com.aya.digital.core.ext

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.aya.digital.core.networkbase.server.IServerError

private val geolocationPermissionsList = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

fun Fragment.getGeolocationPermissions(
    preciseLocationGranted: () -> Unit,
    coarseLocationGranted: () -> Unit,
    noLocationGranted: () -> Unit
) =
    this.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                preciseLocationGranted()
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                coarseLocationGranted()
            }

            else -> {
                noLocationGranted()
            }
        }
    }

fun Fragment.checkGeolocationPermissions(
    preciseLocationGranted: (granted: Boolean) -> Unit,
    coarseLocationGranted: (granted: Boolean) -> Unit
) {

    preciseLocationGranted(
        this.requireActivity().checkPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)
    )
    coarseLocationGranted(
        this.requireActivity()
            .checkPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION/**/)
    )
}

fun Context.checkPermissionGranted(permission: String): Boolean =
    checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

fun Fragment.requestGeolocationPermissions(resultLauncher: ActivityResultLauncher<Array<String>>){

}

