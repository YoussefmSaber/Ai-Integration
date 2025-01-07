package com.saber.aiintegration.domain.repository

import com.saber.aiintegration.data.datasource.LandmarkDao
import com.saber.aiintegration.data.datasource.LandmarkEntity

class LandmarksRepository(private val landmarkDao: LandmarkDao) {

    suspend fun getLandmarks(): List<LandmarkEntity> {
        return landmarkDao.getLandmarks()
    }

    suspend fun insertLandmark(landmark: LandmarkEntity) {
        return landmarkDao.insertLandmark(landmark)
    }
}