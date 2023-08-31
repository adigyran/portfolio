package com.aya.digital.core.domain.home.lastupdates.impl

import android.content.Context
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.home.lastupdates.GetLastUpdatesUseCase
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable

class GetLastUpdatesUseCaseImpl(
    private val context: Context,
    private val progressRepository: ProgressRepository
) : GetLastUpdatesUseCase {
    override fun invoke(): Observable<RequestResult<HashMap<String, LastUpdatesModel>>> {
        TODO("Not yet implemented")
    }
}