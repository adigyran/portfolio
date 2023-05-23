package com.aya.digital.core.database.di

import androidx.room.Room
import com.aya.digital.core.database.AyaDatabase
import com.aya.digital.core.database.dao.DoctorsDao
import org.kodein.di.*

fun dataBaseDiModule() = DI.Module("dataBaseDiModule") {
    bind<AyaDatabase>() with singleton {
        Room.databaseBuilder(
            instance(),
            AyaDatabase::class.java,
            "aya-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    bind<DoctorsDao>() with singleton {
        (instance() as AyaDatabase).doctorsDao()
    }
}