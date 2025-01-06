package com.saber.aiintegration.data

import android.content.Context
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.IOException

/**
 * Manages the models stored in the application's file directory.
 *
 * @property context The application context.
 */
class ModelManager(private val context: Context) {

    private val modelDirectory = File(context.filesDir, "models")

    init {
        if (!modelDirectory.exists()) {
            modelDirectory.mkdir()
        }
    }

    /**
     * Retrieves the file path of a model if it exists.
     *
     * @param modelName The name of the model.
     * @return The file path of the model, or null if it does not exist.
     */
    fun getModelPath(modelName: String): File? {
        val models = mapOf(
            "Europe" to "landmarks-europe.tflite",
            "Asia" to "landmarks-asia.tflite",
            "Africa" to "landmarks-africa.tflite",
            "North America" to "landmarks-north-america.tflite",
            "South America" to "landmarks-south-america.tflite",
            "Oceania" to "landmarks-oceania-antarctica.tflite",
        )
        val modelFile = models[modelName]?.let { File(modelDirectory, it) }
        return if (modelFile != null) {
            if (modelFile.exists()) modelFile else null
        } else null
    }

    /**
     * Downloads a model from a given URL and saves it to the model directory.
     *
     * @param modelName The name of the model.
     * @param modelUrl The URL to download the model from.
     * @param onDownloadComplete Callback function to be invoked when the download is complete.
     */
    fun downloadModel(modelName: String, modelUrl: String, onDownloadComplete: (Boolean) -> Unit) {
        val modelFile = File(modelDirectory, modelName)

        if (modelFile.exists()) {
            onDownloadComplete(true)
            return
        }

        val request = Request.Builder()
            .url(modelUrl)
            .build()

        val okhttpClient = OkHttpClient()
        okhttpClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                onDownloadComplete(false)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    onDownloadComplete(false)
                    return
                }

                modelFile.outputStream().use { output ->
                    response.body?.byteStream()?.copyTo(output)
                }
                onDownloadComplete(true)
            }
        })
    }

    /**
     * Deletes models from the model directory that are not in the list of models to keep.
     *
     * @param keepModels A list of model names to keep.
     */
    fun clearUnusedModels(keepModels: List<String>) {
        modelDirectory.listFiles()?.forEach { file ->
            if (!keepModels.contains(file.nameWithoutExtension)) {
                file.delete()
            }
        }
    }
}