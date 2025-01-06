package com.saber.aiintegration.presentation.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saber.aiintegration.data.datasource.LandmarkEntity
import com.saber.aiintegration.data.manager.ModelManager
import com.saber.aiintegration.domain.usecases.InsertLandmarkUseCase
import com.saber.aiintegration.utils.bitmapToByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LandmarkClassifierViewModel(
    private val modelManager: ModelManager,
    private val insertLandmarkUseCase: InsertLandmarkUseCase,
) : ViewModel() {
    private val _availableModels = MutableStateFlow<List<String>>(emptyList())
    val availableModels: StateFlow<List<String>> = _availableModels

    private val _selectedModel = MutableStateFlow("")
    val selectedModel: StateFlow<String> = _selectedModel

    init {
        loadDownloadedModels()
    }

    private fun loadDownloadedModels() {
        val models = modelManager.getDownloadedModels()
        _availableModels.value = models
        if (models.isNotEmpty()) {
            _selectedModel.value = models.first()
        }
    }

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