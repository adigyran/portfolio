package com.aya.digital.core.domain.schedule.doctor.scheduler.impl

import android.icu.util.Calendar
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.domain.schedule.doctor.scheduler.GetMonthScheduleUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.ScheduleDayModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

internal class GetMonthScheduleUseCaseImpl() : GetMonthScheduleUseCase {
    override fun invoke(month: YearMonth): Single<RequestResultModel<List<ScheduleDayModel>>> =
        Single.just(month)
            .map { yearMonth ->
                getDaysInMonth(yearMonth = yearMonth).map { localDate -> ScheduleDayModel(date = localDate) }
            }
            .map { it.asResultModel() }


    private fun getDaysInMonth(yearMonth: YearMonth) =
        mutableListOf<LocalDate>().apply {
            for (day in 1..yearMonth.lengthOfMonth()) {
                add(yearMonth.atDay(day))
            }
        }
}