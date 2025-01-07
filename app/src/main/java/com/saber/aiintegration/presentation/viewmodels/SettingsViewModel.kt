package com.saber.aiintegration.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.saber.aiintegration.data.manager.ModelManager
import com.saber.aiintegration.utils.MODELS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(
    private val modelManager: ModelManager,
) : ViewModel() {

    private val _availableModels = MutableStateFlow<List<String>>(emptyList())
    val availableModels: StateFlow<List<String>> = _availableModels

    private val _showDialog = MutableStateFlow(false) // New state for showing the dialog
    val showDialog: StateFlow<Boolean> get() = _showDialog

    private val _dialogType = MutableStateFlow<DialogType?>(null) // Type of the dialog
    val dialogType: StateFlow<DialogType?> get() = _dialogType

    private val _selectedModel = MutableStateFlow<String?>(null)
    val selectedModel: StateFlow<String?> = _selectedModel

    val modelDownloadProgress: StateFlow<Float> = modelManager.downloadProgress

    init {
        loadDownloadedModels()
    }

    private fun loadDownloadedModels() {
        val models = modelManager.getDownloadedModels()
        Log.d("Settings", "loadDownloadedModels: $models")
        _availableModels.value = models
    }

    fun downloadModel(modelName: String) {
        modelManager.ensureModelAvailable(modelName, onDownloadComplete = { success, _ ->
            if (success) {
                loadDownloadedModels()
            }
        })
        loadDownloadedModels()
    }

    fun showRemoveDialog(modelName: String) {
        _dialogType.value = DialogType.REMOVE
        _showDialog.value = true
        _selectedModel.value = modelName
    }

    fun showDownloadDialog(modelName: String) {
        _dialogType.value = DialogType.DOWNLOAD
        _showDialog.value = true
        _selectedModel.value = modelName
    }

    fun dismissDialog() {
        _showDialog.value = false
        _dialogType.value = null
    }

    enum class DialogType {
        REMOVE, DOWNLOAD
    }
}
