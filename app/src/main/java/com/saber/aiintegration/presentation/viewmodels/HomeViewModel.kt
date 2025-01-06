package com.saber.aiintegration.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saber.aiintegration.data.datasource.LandmarkEntity
import com.saber.aiintegration.domain.usecases.GetLandmarksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLandmarksUseCase: GetLandmarksUseCase,
): ViewModel() {

    private val _landmarks = MutableStateFlow<List<LandmarkEntity>>(emptyList())
    val landmarks: StateFlow<List<LandmarkEntity>> = _landmarks

    init {
        loadLandmarks()
    }

    private fun loadLandmarks() {
        viewModelScope.launch(Dispatchers.IO) {
            _landmarks.value = getLandmarksUseCase.invoke()
        }
    }
}