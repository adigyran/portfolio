package com.aya.digital.core.data.appointment.repository

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface AppointmentRepository {

    fun getRoomTokenById(roomId:Int):Single<RequestResult<String>>

    fun getAppointments():Flowable<RequestResult<List<Appointment>>>
}