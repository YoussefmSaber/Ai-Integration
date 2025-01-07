package com.saber.aiintegration.data.manager

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.Surface
import com.saber.aiintegration.domain.classification.Classification
import com.saber.aiintegration.domain.classification.LandmarkClassifier
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class TfLiteLandmarkClassifier(
    private val context: Context,
    private val threshold: Float = 0.6f,
    private val maxResult: Int = 1
) : LandmarkClassifier {

    private var classifier: ImageClassifier? = null
    private var currentModel: String? = null
    private var isModelLoaded = false  // Flag to track model load status

    fun loadModel(modelName: String) {
        loadModelFromAssets(modelName)
    }

    private fun loadModelFromAssets(modelName: String) {
        try {
            val baseOptions = BaseOptions.builder()
                .setNumThreads(2)
                .build()
            val options = ImageClassifier.ImageClassifierOptions.builder()
                .setBaseOptions(baseOptions)
                .setMaxResults(maxResult)
                .setScoreThreshold(threshold)
                .build()

            // Close the previous classifier if it's already loaded
            classifier?.close()

            classifier = ImageClassifier.createFromFileAndOptions(context, modelName, options)
            isModelLoaded = true  // Set the flag to true when the model is loaded

            Log.d("TfLiteLandmarkClassifier", "Model loaded successfully from assets")
            currentModel = modelName

        } catch (e: Exception) {
            Log.e("TfLiteLandmarkClassifier", "Failed to load model from assets: ${e.message}")
        }
    }


    override fun classify(bitmap: Bitmap, rotation: Int, location: String): List<Classification> {
        Log.d("TAG", "classifier: $classifier")
        Log.d("TAG", "model: $currentModel")
        if (!isModelLoaded) {
            Log.e("TfLiteLandmarkClassifier", "Model is not loaded yet")
            return emptyList()
        }

        val imageProcessor = ImageProcessor.Builder().build()
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))
        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(getOrientationFromRotation(rotation))
            .build()

        val result = classifier?.classify(tensorImage, imageProcessingOptions)
        return result?.flatMap { classifications ->
            classifications.categories.map { category ->
                Log.d("TfLiteLandmarkClassifier", "classify: $category")
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