package com.saber.aiintegration.di

import android.content.Context
import androidx.room.Room
import com.saber.aiintegration.data.datasource.LandmarksDatabase
import com.saber.aiintegration.data.manager.ModelManager
import com.saber.aiintegration.presentation.viewmodels.LandmarkClassifierViewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val appModule = module {

    // Provide the ModelManager as a singleton
    single { (context: Context) -> ModelManager(context) }

    // Provide the ViewModel
    viewModel { (context: Context) ->
        LandmarkClassifierViewModel(get { parametersOf(context) })
    }

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