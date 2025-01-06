package com.saber.aiintegration.utils

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.saber.aiintegration.domain.Classification
import com.saber.aiintegration.domain.LandmarkClassifier

class LandmarkImageAnalyzer(
    private val classifier: LandmarkClassifier,
    val location: String = "Europe",
    private val onResults: (List<Classification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image.toBitmap()
            val result = classifier.classify(bitmap, rotationDegrees, location)

            onResults(result)
        }
        frameSkipCounter++
        image.close()
    }
}