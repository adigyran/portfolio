package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.network.model.request.SlotBody
import com.aya.digital.core.network.model.response.SlotResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.*

interface ScheduleService {

    @GET("slots")
    fun fetchSlots(
        @Query("practitionerId") practitionerId: Int,
        @Query("start") start: String,
        @Query("end") end: String
    ): Flowable<List<SlotResponse>>

    @POST("schedules/with-slots")
    fun create(
        @Body scheduleWithSlots: ScheduleWithSlotsBody
    ): Completable

    @GET("slots/{id}")
    fun getSlot(
        @Path("id") id: Int
    ): Single<SlotResponse>

    @DELETE("slots/{id}")
    fun deleteSlot(
        @Path("id") id: Int
    ): Completable

    @POST("slots/")
    fun createSlot(
        @Body body: SlotBody
    ): Completable

    @PUT("slots/{id}")
    fun updateSlot(
        @Path("id") id: Int,
        @Body body: SlotBody
    ): Completable
}