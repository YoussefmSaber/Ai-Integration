package com.saber.aiintegration.viewmodel

import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import com.saber.aiintegration.shared.ImageProcessingUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.tensorflow.lite.Interpreter

class BackgroundRemovalViewModel(private val interpreter: Interpreter) : ViewModel() {

    private val _processedImage = MutableStateFlow<Bitmap?>(null)
    val processedImage: StateFlow<Bitmap?> = _processedImage

    /**
     * Processes the given image by removing its background.
     *
     * @param imageProxy The image to be processed.
     */
    fun processImage(imageProxy: ImageProxy) {
        // Convert ImageProxy to Bitmap
        val bitmap = ImageProcessingUtils.imageProxyToBitmap(imageProxy)

        // Resize the image to 257x257
        val resizedImage = Bitmap.createScaledBitmap(bitmap, 257, 257, true)

        // Preprocess the image for the model
        val input = ImageProcessingUtils.preprocessImage(resizedImage, 257, 257)

        // Prepare the output array for the model
        val output = Array(257) { FloatArray(257) }

        // Run the model inference
        interpreter.run(input, output)

        // Create a mask from the model output
        val mask = ImageProcessingUtils.createMask(output)

        // Apply the mask to the original image
        val result = ImageProcessingUtils.applyMaskToImage(bitmap, mask)

        // Update the processed image state
        _processedImage.value = result

        // Close the ImageProxy
        imageProxy.close()
    }
}