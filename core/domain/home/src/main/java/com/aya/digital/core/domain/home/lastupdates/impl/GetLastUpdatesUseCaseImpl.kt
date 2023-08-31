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

const val FILE_NAME = "releasenotes.txt"

internal class GetLastUpdatesUseCaseImpl(
    private val context: Context,
    private val progressRepository: ProgressRepository
) : GetLastUpdatesUseCase {
    override fun invoke(): Single<RequestResultModel<HashMap<String, LastUpdatesModel>>> =
        Single.just(FILE_NAME)
            .trackProgress(progressRepository)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { fileName ->
                val list = readAsset(context, fileName)
                list
            }
            .map { list ->
                val updates: HashMap<String, LastUpdatesModel> = hashMapOf()
                val updateItems = mutableListOf<LastUpdatesItem>()
                list.forEach { line ->
                    if (line.contains("version",true)) {
                        updates[line] = LastUpdatesModel(line, updateItems)
                        updateItems.clear()
                    } else {
                        updateItems.add(LastUpdatesItem(line))
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
        return list
    }

}