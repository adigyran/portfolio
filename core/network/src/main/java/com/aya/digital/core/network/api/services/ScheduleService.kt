package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.network.model.request.SlotBody
import com.aya.digital.core.network.model.response.SlotResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.schedule.Schedule
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.Response
import retrofit2.http.*

interface ScheduleService {

    @GET("schedules/")
    fun fetchSchedules(
        @Query("practitioner") practitionerId: Int,
        @Query("start") start: LocalDate,
        @Query("end") end: LocalDate,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Flowable<PagedResponse<Schedule>>

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