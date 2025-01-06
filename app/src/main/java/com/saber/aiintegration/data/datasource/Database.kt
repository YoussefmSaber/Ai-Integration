package com.saber.aiintegration.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LandmarkEntity::class], version = 1, exportSchema = false)
abstract class LandmarksDatabase() : RoomDatabase() {
    abstract fun landmarkDao(): LandmarkDao
}