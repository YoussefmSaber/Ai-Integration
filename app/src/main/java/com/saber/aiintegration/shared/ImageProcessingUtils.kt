package com.saber.aiintegration.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageFormat
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object ImageProcessingUtils {

    /**
     * Preprocesses the given bitmap by scaling it to the specified dimensions and converting it to a ByteBuffer.
     *
     * @param bitmap The input Bitmap to preprocess.
     * @param inputWidth The target width for the scaled bitmap.
     * @param inputHeight The target height for the scaled bitmap.
     * @return A ByteBuffer containing the preprocessed image data.
     */
    fun preprocessImage(bitmap: Bitmap, inputWidth: Int, inputHeight: Int): ByteBuffer {
        // Scale the input bitmap to the specified dimensions
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, inputWidth, inputHeight, true)

        // Allocate a ByteBuffer to hold the preprocessed image data
        val byteBuffer = ByteBuffer.allocateDirect(inputWidth * inputHeight * 3 * 4).apply {
            // Set the byte order to the native order of the underlying platform
            order(ByteOrder.nativeOrder())
        }

        // Iterate over each pixel in the scaled bitmap
        for (y in 0 until inputHeight) {
            for (x in 0 until inputWidth) {
                // Get the pixel value at the specified coordinates
                val pixel = scaledBitmap.getPixel(x, y)

                // Extract the red, green, and blue components of the pixel and normalize them
                byteBuffer.putFloat((pixel shr 16 and 0xFF) / 255.0F) // Getting the Red components
                byteBuffer.putFloat((pixel shr 8 and 0xFF) / 255.0F) // Getting the Green components
                byteBuffer.putFloat((pixel and 0xFF) / 255.0F) // Getting the Blue components
            }
        }

        // Return the ByteBuffer containing the preprocessed image data
        return byteBuffer
    }

    /**
     * Creates a mask bitmap from the given output array.
     *
     * @param output A 2D array of Float values representing the mask.
     * @return A Bitmap where each pixel is either transparent or black based on the output values.
     */
    fun createMask(output: Array<FloatArray>): Bitmap {
        // Create a bitmap with the same dimensions as the output array
        val mask = Bitmap.createBitmap(output[0].size, output[0].size, Bitmap.Config.ARGB_8888)

// Iterate over each row in the output array
        for (y in output.indices) {
            // Iterate over each column in the current row
            for (x in output[y].indices) {
                // Determine if the current pixel is part of the background
                val isBackground = output[y][x] < 0.5
                // Set the pixel color to transparent if it is background, otherwise set it to black
                val color = if (isBackground) Color.TRANSPARENT else Color.BLACK
                // Set the pixel color in the mask bitmap
                mask.setPixel(x, y, color)
            }
        }

// Return the created mask bitmap
        return mask
    }

    /**
     * Applies a mask to the given image.
     *
     * @param image The original Bitmap image.
     * @param mask The Bitmap mask to apply to the image.
     * @return A new Bitmap with the mask applied.
     */
    fun applyMaskToImage(image: Bitmap, mask: Bitmap): Bitmap {
        // Create a new Bitmap with the same dimensions as the original image
        val result = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)

        // Create a Canvas to draw on the new Bitmap
        val canvas = Canvas(result)

        // Create a Paint object to define the drawing properties
        val paint = Paint()

        // Draw the original image onto the canvas
        canvas.drawBitmap(image, 0f, 0f, paint)

        // Set the paint's xfermode to DST_IN to apply the mask
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

        // Draw the mask onto the canvas, applying it to the image
        canvas.drawBitmap(mask, 0f, 0f, paint)

        // Return the resulting Bitmap with the mask applied
        return result
    }

    /**
     * Converts an ImageProxy to a Bitmap.
     *
     * @param imageProxy The ImageProxy to convert.
     * @return A Bitmap representation of the ImageProxy.
     */
    fun imageProxyToBitmap(imageProxy: ImageProxy): Bitmap {
        // Get the buffer from the first plane of the ImageProxy
        val buffer = imageProxy.planes[0].buffer
        // Create a byte array to hold the buffer data
        val bytes = ByteArray(buffer.remaining())
        // Copy the buffer data into the byte array
        buffer.get(bytes)
        // Create a YuvImage from the byte array with the specified format and dimensions
        val yuvImage = YuvImage(bytes, ImageFormat.NV21, imageProxy.width, imageProxy.height, null)
        // Create a ByteArrayOutputStream to hold the JPEG data
        val out = ByteArrayOutputStream()
        // Compress the YuvImage to JPEG format and write it to the ByteArrayOutputStream
        yuvImage.compressToJpeg(Rect(0, 0, imageProxy.width, imageProxy.height), 100, out)
        // Convert the ByteArrayOutputStream to a byte array
        val byteArray = out.toByteArray()
        // Decode the byte array to a Bitmap and return it
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}