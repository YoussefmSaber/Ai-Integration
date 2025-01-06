package com.saber.aiintegration.di

import androidx.room.Room
import com.saber.aiintegration.data.datasource.LandmarksDatabase
import org.koin.dsl.module

val appModule = module {
    single {
        get<LandmarksDatabase>().landmarkDao()
    }

    single {
        Room.databaseBuilder(
            get(),
            LandmarksDatabase::class.java,
            "landmarks_database"
        ).build()
    }


}