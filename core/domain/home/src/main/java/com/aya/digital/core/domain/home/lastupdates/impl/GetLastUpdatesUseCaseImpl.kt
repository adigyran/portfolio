package com.aya.digital.core.domain.home.lastupdates.impl

import android.content.Context
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.home.lastupdates.GetLastUpdatesUseCase
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesItem
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.io.BufferedReader
import java.util.regex.Pattern

const val FILE_NAME = "releasenotes.txt"

internal class GetLastUpdatesUseCaseImpl(
    private val context: Context,
    private val progressRepository: ProgressRepository
) : GetLastUpdatesUseCase {
    override fun invoke(): Single<RequestResultModel<LinkedHashMap<String, LastUpdatesModel>>> =
        Single.just(FILE_NAME)
            .trackProgress(progressRepository)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { fileName ->
                val list = readAsset(context, fileName)
                list
            }
            .map { list ->
                val pattern = Regex("\\bversion\\b")
                val updates: LinkedHashMap<String, LastUpdatesModel> = linkedMapOf()
                val updateItems = mutableListOf<String>()
                list.forEach { line ->
                    if (line.contains(pattern)) {
                        updates[line] = LastUpdatesModel(line, updateItems.map { LastUpdatesItem(it) })
                        updateItems.clear()
                    } else if(line.isNotBlank()) {
                        updateItems.add(line)
                    }
                }
                updates.asResultModel()
            }

    fun readAsset(context: Context, fileName: String): List<String> {
        val list = mutableListOf<String>()
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .forEachLine {
                list.add(it)
            }
        return list.asReversed()
    }

}