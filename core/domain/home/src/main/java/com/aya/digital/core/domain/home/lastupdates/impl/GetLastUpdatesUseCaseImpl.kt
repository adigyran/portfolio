package com.aya.digital.core.domain.home.lastupdates.impl

import android.content.Context
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.home.lastupdates.GetLastUpdatesUseCase
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesItem
import com.aya.digital.core.domain.home.lastupdates.model.LastUpdatesModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.BufferedReader

const val FILE_NAME = "releasenotes.txt"

class GetLastUpdatesUseCaseImpl(
    private val context: Context,
    private val progressRepository: ProgressRepository
) : GetLastUpdatesUseCase {
    override fun invoke(): Single<RequestResultModel<HashMap<String, LastUpdatesModel>>> =
        Single.just(FILE_NAME)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { fileName -> readAsset(context, fileName) }
            .map { sequence ->
                sequence.associateBy { s: String -> s.contains("version",true) }
            }
            .map { map ->
                val updates: HashMap<String, LastUpdatesModel> = hashMapOf()
                val updateItems = mutableListOf<LastUpdatesItem>()
                map.forEach { entry ->
                    if(entry.key)
                    {
                        updates[entry.value] = LastUpdatesModel(entry.value,updateItems)
                        updateItems.clear()
                    }
                    else
                    {
                        updateItems.add(LastUpdatesItem(entry.value))
                    }
                }
                updates.asResultModel()
            }

    fun readAsset(context: Context, fileName: String): Sequence<String> =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::lineSequence)
}