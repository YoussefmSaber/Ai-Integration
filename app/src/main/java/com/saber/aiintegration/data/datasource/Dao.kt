package com.saber.aiintegration.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LandmarkDao {

    @Insert
    suspend fun insertLandmark(landmark: LandmarkEntity)

    @Query("Select * from images")
    suspend fun getLandmarks(): List<LandmarkEntity>
}