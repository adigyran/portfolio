package com.aya.digital.core.repository.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.location.repository.LocationRepository
import com.aya.digital.core.data.location.repository.PermissionsRepository
import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class LocationRepositoryImpl(
    private val context: Context,
    private val permissionsRepository: PermissionsRepository
) : LocationRepository {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun getLocation(): Observable<LocationRepository.Result> {
        TODO("Not yet implemented")
    }


}