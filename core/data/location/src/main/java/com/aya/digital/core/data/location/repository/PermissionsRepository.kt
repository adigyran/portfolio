package com.aya.digital.core.data.location.repository

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import io.reactivex.rxjava3.core.Observable

interface PermissionsRepository {

    data class Permission(
        val permission: String,
        val shouldShowRequestPermissionRationale: Boolean,
    )

    data class Result(
        val requestCode: Int,
        val items: List<Permission>,
    )

    fun check(vararg permissions: String): Observable<List<Permission>>

    fun request(permissions: Array<Permission>): Observable<Result>
    companion object {
        const val CAMERA = Manifest.permission.CAMERA

      /*  fun getPermissionList(
            activity: Activity,
            permissions: Array<out String>,
            grantResults: IntArray,
        ): List<Permission> = permissions.mapIndexedNotNull { index, s ->
            if (grantResults[index] == PackageManager.PERMISSION_DENIED)
                Permission(s, ActivityCompat.shouldShowRequestPermissionRationale(activity, s))
            else null
        }*/
    }

}
