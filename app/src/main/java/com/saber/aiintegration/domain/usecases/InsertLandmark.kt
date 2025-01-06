package com.saber.aiintegration.domain.usecases

import com.saber.aiintegration.data.datasource.LandmarkEntity
import com.saber.aiintegration.domain.repository.LandmarksRepository

class InsertLandmarkUseCase(private val landmarksRepository: LandmarksRepository) {
    suspend operator fun invoke(landmark: LandmarkEntity) {
        landmarksRepository.insertLandmark(landmark)
    }
}