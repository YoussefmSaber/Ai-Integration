package com.saber.aiintegration.presentation

import android.graphics.Bitmap

fun Bitmap.centerCrop(width: Int, height: Int): Bitmap {
    val xStart = (this.width - width) / 2
    val yStart = (this.height - height) / 2

    if (xStart < 0 || yStart < 0 || width > this.width || height > this.height) {
        throw IllegalArgumentException("Invalid arguments for center cropping")
    }

    return Bitmap.createBitmap(this, xStart, yStart, width, height)
}