package com.saber.aiintegration.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.Surface
import com.saber.aiintegration.domain.Classification
import com.saber.aiintegration.domain.LandmarkClassifier
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class TfLiteLandmarkClassifier(
    private val context: Context,
    private val modelManager: ModelManager,
    private val threshold: Float = 0.6f,
    private val maxResult: Int = 1
) : LandmarkClassifier {

    private var classifier: ImageClassifier? = null
    private var currentModel: String? = null

    private val models = mapOf(
        "Europe" to "https://drive.usercontent.google.com/u/0/uc?id=1BNRTjgMbEJYQ0dDRWJpNj8ChDWUWqmfj&export=download",
        "Asia" to "https://drive.usercontent.google.com/u/0/uc?id=1N6ol2Rj1PhC5dsv9Dumznx6Va_CZtc5S&export=download",
        "Africa" to "https://drive.usercontent.google.com/u/0/uc?id=1Gvwq5aOI1vr0CCvfiB0NidsXcC2ptD8C&export=download",
        "North America" to "https://drive.usercontent.google.com/u/0/uc?id=1hSvrBH5Ysi9g5sbfQkZk7aWBHixoIfNG&export=download",
        "South America" to "https://drive.usercontent.google.com/u/0/uc?id=1Fzo15xUdFemTtxqPUUfm8OgzRjfCht7V&export=download",
        "Oceania" to "https://drive.usercontent.google.com/u/0/uc?id=1v0x1uvpsez4wQcoMRDboBSCtpDFqgpM3&export=download",
    )

    private fun setupClassifier(modelName: String, onDownloadComplete: (Boolean) -> Unit) {
        val modelUrl = models[modelName] ?: return onDownloadComplete(false)

        modelManager.ensureModelAvailable(modelName, modelUrl) { success, modelFile ->
            if (success && modelFile != null) {
                loadModel(modelFile.absolutePath)
            }
            onDownloadComplete(success)
        }
    }

    private fun loadModel(modelPath: String) {
        val baseOptions = BaseOptions.builder()
            .setNumThreads(2)
            .build()
        val options = ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(baseOptions)
            .setMaxResults(maxResult)
            .setScoreThreshold(threshold)
            .build()

        try {
            classifier?.close()
            classifier = ImageClassifier.createFromFileAndOptions(
                context,
                modelPath,
                options
            )
            currentModel = modelPath

        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun classify(
        bitmap: Bitmap,
        rotation: Int,
        location: String
    ): List<Classification> {
        if (classifier == null || currentModel == null) return emptyList()

        val imageProcessor = ImageProcessor.Builder().build()
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))
        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(getOrientationFromRotation(rotation))
            .build()

        val result = classifier?.classify(tensorImage, imageProcessingOptions)

        return result?.flatMap { classifications ->
            classifications.categories.map { category ->
                Log.d("TAG", "classify: $category")
                Classification(category.displayName, category.score)
            }
        }?.distinctBy { it.label } ?: emptyList()
    }

    private fun getOrientationFromRotation(rotation: Int): ImageProcessingOptions.Orientation {
        return when (rotation) {
            Surface.ROTATION_270 -> ImageProcessingOptions.Orientation.BOTTOM_RIGHT
            Surface.ROTATION_90 -> ImageProcessingOptions.Orientation.TOP_LEFT
            Surface.ROTATION_180 -> ImageProcessingOptions.Orientation.RIGHT_BOTTOM
            else -> ImageProcessingOptions.Orientation.RIGHT_TOP
        }
    }
}