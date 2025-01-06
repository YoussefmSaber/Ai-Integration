package com.saber.aiintegration.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import java.io.ByteArrayOutputStream

/**
 * Applies a fading edge effect to a composable using a specified brush.
 *
 * This modifier creates a fading edge by drawing a rectangle with the given brush
 * on top of the composable's content, using a Darken blend mode.
 *
 * @param brush The brush to use for the fading edge.
 * @return A modifier that applies the fading edge effect.
 */
fun Modifier.fadingEdge(brush: Brush) = this
    /**
     *  Apply a graphics layer with offscreen compositing strategy.
     *
     * This ensures that the fading edge is drawn separately and doesn't affect the composable's content.
     */
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    /**
     * Draw the composable's content and then draw a rectangle with the brush.
     */
    .drawWithContent {
        /**
         * Draw the original content of the composable.
         */
        drawContent()
        /**
         * Draw a rectangle over the content using the provided brush and Darken blend mode.
         * This creates the fading edge effect.
         */
        drawRect(brush = brush, blendMode = BlendMode.Darken, alpha = 0.5F)
    }

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream) // Or JPEG, based on your preference
    return stream.toByteArray()
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}