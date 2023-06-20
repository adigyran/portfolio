package com.aya.digital.core.domain.root.progress.impl

import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.root.progress.ListenForProgressUseCase
import io.reactivex.rxjava3.core.Observable

internal class ListenForProgressUseCaseImpl(private val progressRepository: ProgressRepository) :
    ListenForProgressUseCase {
    override fun invoke(): Observable<Boolean> = progressRepository.listenForProgress()
}