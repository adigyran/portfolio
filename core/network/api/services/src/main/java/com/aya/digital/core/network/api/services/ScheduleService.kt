package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.network.model.request.SlotBody
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse
import com.aya.digital.core.network.model.response.schedule.SlotResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ScheduleService {
    @GET("api/schedules/freeslots")
    fun getFreeSlotsSchedule(
        @Query("practitionerId") practitionerId: Int,
        @Query("start") start: String,
        @Query("end") end: String
    ): Flowable<List<ScheduleResponse>>

    @GET("api/slots")
    fun fetchSlots(
        @Query("practitionerId") practitionerId: Int,
        @Query("start") start: String,
        @Query("end") end: String
    ): Flowable<List<SlotResponse>>

    @POST("api/schedules/with-slots")
    fun create(
        @Body scheduleWithSlots: ScheduleWithSlotsBody
    ): Completable

    @GET("api/slots/{id}")
    fun getSlot(
        @Path("id") id: Int
    ): Single<SlotResponse>

    @DELETE("api/slots/{id}")
    fun deleteSlot(
        @Path("id") id: Int
    ): Completable

    @POST("api/slots/")
    fun createSlot(
        @Body body: SlotBody
    ): Completable

    @PUT("api/slots/{id}")
    fun updateSlot(
        @Path("id") id: Int,
        @Body body: SlotBody
    ): Completable
}