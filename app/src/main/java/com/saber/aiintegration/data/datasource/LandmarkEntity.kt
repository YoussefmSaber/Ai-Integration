package com.saber.aiintegration.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class LandmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val landmarkName: String,
    val imageData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LandmarkEntity

        if (id != other.id) return false
        if (landmarkName != other.landmarkName) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + landmarkName.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}
