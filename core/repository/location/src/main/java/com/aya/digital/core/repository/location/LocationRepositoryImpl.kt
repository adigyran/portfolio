package com.aya.digital.core.repository.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.annotation.RequiresPermission
import com.aya.digital.core.data.location.LocResult
import com.aya.digital.core.data.location.Result
import com.aya.digital.core.data.location.repository.LocationRepository
import com.aya.digital.core.ext.*
import com.aya.digital.core.navigation.utils.OperationResult
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable

internal class LocationRepositoryImpl(
    private val context: Context
) : LocationRepository {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)


    @SuppressLint("MissingPermission")
    private fun checkLocationSettingsAndGetLocationRecursive(needToResolveException: Boolean): Observable<LocResult> =
        checkLocationSettings()
            .flatMap {
                when (it) {
                    is OperationResult.Ok -> {
                        tryToGetLastLocationRecursive(true)
                    }

                    is OperationResult.Fail -> {

                        if (it.payload !is ResolvableApiException) {

                            return@flatMap Observable.just(LocResult.UnresolvableException(it.payload))
                        }

                        if (needToResolveException) {

                            tryToResolveCheckLocationSettingsException(it.payload as ResolvableApiException)
                                .flatMap {
                                    when (it) {
                                        is OperationResult.Ok -> {

                                            checkLocationSettingsAndGetLocationRecursive(false)
                                        }

                                        is OperationResult.Fail -> {

                                            Observable.just(LocResult.CheckLocationSettingsExceptionNotResolved)
                                        }
                                    }
                                }
                        } else {

                            Observable.just(LocResult.CheckLocationSettingsExceptionNotResolved)
                        }
                    }
                }
            }

    @SuppressLint("MissingPermission")
    private fun tryToGetLastLocationRecursive(needToResolveException: Boolean): Observable<LocResult> =
        tryToGetLastLocation()
            .flatMap {
                when (it) {
                    is OperationResult.Ok -> {
                        if (it.payload == null) {

                            getLastLocationUpdates()
                                .flatMap {
                                    when (it) {
                                        is OperationResult.Ok -> {
                                            if (it.payload == null) {

                                                Observable.just(LocResult.CoordinatesNotAvailable)
                                            } else {

                                                Observable.just(LocResult.Success(it.payload as Location))
                                            }
                                        }

                                        is OperationResult.Fail -> {

                                            Observable.just(LocResult.CoordinatesNotAvailable)
                                        }
                                    }
                                }
                        } else {

                            Observable.just(LocResult.Success(it.payload as Location))
                        }
                    }

                    is OperationResult.Fail -> {


                        if (it.payload !is ResolvableApiException) {

                            return@flatMap Observable.just(LocResult.UnresolvableException(it.payload))
                        }

                        if (needToResolveException) {

                            return@flatMap tryToResolveLastLocationGettingException(it.payload as ResolvableApiException)
                                .flatMap {
                                    when (it) {
                                        is OperationResult.Ok -> {

                                            tryToGetLastLocationRecursive(false)
                                        }

                                        is OperationResult.Fail -> {
                                            Observable.just(LocResult.LastLocationExceptionNotResolved)
                                        }
                                    }
                                }
                        } else {

                            Observable.just(LocResult.LastLocationExceptionNotResolved)
                        }
                    }
                }
            }

    //Проверка настроек геолокации
    private fun checkLocationSettings(): Observable<OperationResult<Unit, Exception>> =
        Observable.create { emitter ->
            val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000)

            val locationSettingsRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build()

            LocationServices.getSettingsClient(context)
                .checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener { response ->
                    emitter.onNext(OperationResult.Ok(Unit))
                    emitter.onComplete()
                }
                .addOnFailureListener { exception ->
                    emitter.onNext(OperationResult.Fail(exception))
                    emitter.onComplete()
                }
        }

    //Попытка разрешить исключение, возникшее при проверке настроек геолокации
    private fun tryToResolveCheckLocationSettingsException(ex: ResolvableApiException): Observable<OperationResult<Unit, Unit>> {
        TODO("Not yet implemented")
    }





    //Попытка достать последнюю геопозицию
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @SuppressWarnings("MissingPermission")
    @SuppressLint("MissingPermission")
    private fun tryToGetLastLocation(): Observable<OperationResult<Location?, Exception>> =
        Observable.create { emitter ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    emitter.onNext(OperationResult.Ok(location))
                    emitter.onComplete()
                }
                .addOnFailureListener { exception ->
                    emitter.onNext(OperationResult.Fail(exception))
                    emitter.onComplete()
                }
        }

    //Попытка разрешить исключение, возникшее при попытке достать последнюю геопозицию
    private fun tryToResolveLastLocationGettingException(ex: ResolvableApiException): Observable<OperationResult<Unit, Unit>>{
        TODO("Not yet implemented")
    }

    //Получение подписки на последнюю геопозицию
    private fun getLastLocationUpdates(): Observable<OperationResult<Location?, Unit>> =
        Observable.create(UpdateLocationObservableSubscribe(fusedLocationClient))

    private class UpdateLocationObservableSubscribe(private val locationProvider: FusedLocationProviderClient) :
        ObservableOnSubscribe<OperationResult<Location?, Unit>> {
        private val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)
            .setNumUpdates(1)

        @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        @SuppressWarnings("MissingPermission")
        @SuppressLint("MissingPermission")
        override fun subscribe(emitter: ObservableEmitter<OperationResult<Location?, Unit>>) {
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    locationProvider.removeLocationUpdates(this)

                    val lastLocation = result.lastLocation
                    emitter.onNext(OperationResult.Ok(lastLocation))

                    emitter.onComplete()
                }
            }

            locationProvider.requestLocationUpdates(locationRequest, locationCallback, null)
            emitter.setDisposable(Disposable.fromRunnable {
                locationProvider.removeLocationUpdates(
                    locationCallback
                )
            })
        }
    }

    override fun getLocation(): Observable<Result> =
        checkLocationSettingsAndGetLocationRecursive(true)
            .map { Result.Success(it) }

    override fun test() {
        TODO("Not yet implemented")
    }


}