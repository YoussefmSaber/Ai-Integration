package com.saber.aiintegration.presentation.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saber.aiintegration.data.datasource.LandmarkEntity
import com.saber.aiintegration.domain.usecases.InsertLandmarkUseCase
import com.saber.aiintegration.utils.bitmapToByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LandmarkClassifierViewModel(
    private val insertLandmarkUseCase: InsertLandmarkUseCase,
) : ViewModel() {

    private val _selectedModel = MutableStateFlow("Asia")
    val selectedModel: StateFlow<String> = _selectedModel

    fun saveLandmark(landmarkTitle: String, landmarkImage: Bitmap) {
        val imageByteArray = landmarkImage.bitmapToByteArray()
        val landmark = LandmarkEntity(
            id = 0,
            landmarkName = landmarkTitle,
            imageData = imageByteArray
        )
        insertLandmark(landmark)
    }

    private fun insertLandmark(landmark: LandmarkEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            insertLandmarkUseCase.invoke(landmark)
        }
    }

    fun selectModel(modelName: String) {
        _selectedModel.value = modelName
    }
}