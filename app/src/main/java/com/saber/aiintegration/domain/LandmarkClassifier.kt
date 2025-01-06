package com.saber.aiintegration.domain

import android.graphics.Bitmap

interface LandmarkClassifier {

    fun classify(bitmap: Bitmap, rotation: Int, location: String): List<Classification>
}