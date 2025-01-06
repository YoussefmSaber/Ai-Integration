package com.saber.aiintegration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.saber.aiintegration.data.manager.ModelManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LandmarkClassifierViewModel(
    private val modelManager: ModelManager
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

    fun selectModel(modelName: String) {
        _selectedModel.value = modelName
    }
}