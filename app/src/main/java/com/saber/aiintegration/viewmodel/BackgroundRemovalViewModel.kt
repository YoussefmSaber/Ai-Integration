package com.saber.aiintegration.viewmodel

import androidx.lifecycle.ViewModel
import org.tensorflow.lite.Interpreter

class BackgroundRemovalViewModel(private val interpreter: Interpreter) : ViewModel() {
}