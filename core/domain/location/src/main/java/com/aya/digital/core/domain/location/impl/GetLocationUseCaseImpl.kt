package com.aya.digital.core.domain.location.impl

import com.aya.digital.core.data.location.repository.LocationRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.location.GetLocationUseCase
import com.aya.digital.core.domain.location.model.ResultModel
import com.aya.digital.core.domain.location.model.toResultModel
import io.reactivex.rxjava3.core.Observable

internal class GetLocationUseCaseImpl(
    private val progressRepository: ProgressRepository,
    private val locationRepository: LocationRepository
) : GetLocationUseCase {
    override fun invoke(): Observable<ResultModel> = locationRepository.getLocation()
        .trackProgress(progressRepository)
        .map {
            it.toResultModel()
        }
}