package com.saber.aiintegration.domain.usecases

import com.saber.aiintegration.data.datasource.LandmarkEntity
import com.saber.aiintegration.domain.repository.LandmarksRepository

class GetLandmarksUseCase(private val landmarksRepository: LandmarksRepository) {

    suspend operator fun invoke(): List<LandmarkEntity> {
        return landmarksRepository.getLandmarks()
    }
}