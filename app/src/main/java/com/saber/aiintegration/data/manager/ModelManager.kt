package com.saber.aiintegration.data.manager

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
     * Ensures that the specified model is available locally. If the model is not available,
     * it downloads the model from the provided URL.
     *
     * @param modelName The name of the model to check or download.
     * @param modelUrl The URL to download the model from if it is not available locally.
     * @param onDownloadComplete Callback function to be invoked when the model is available.
     *                           The callback receives a Boolean indicating success and the File object of the model.
     */
    fun ensureModelAvailable(
        modelName: String,
        modelUrl: String,
        onDownloadComplete: (Boolean, File?) -> Unit
    ) {
        val models = mapOf(
            "Europe" to "landmarks-europe.tflite",
            "Asia" to "landmarks-asia.tflite",
            "Africa" to "landmarks-africa.tflite",
            "North America" to "landmarks-north-america.tflite",
            "South America" to "landmarks-south-america.tflite",
            "Oceania" to "landmarks-oceania-antarctica.tflite",
        )
        val modelFile = models[modelName]?.let { File(modelDirectory, it) }
        if (modelFile?.exists() == true) {
            onDownloadComplete(true, modelFile)
            return
        }

        downloadModel(modelName, modelUrl) { sucess ->
            onDownloadComplete(sucess, if (sucess) modelFile else null)
        }
    }

    /**
     * Downloads a model from a given URL and saves it to the model directory.
     *
     * @param modelName The name of the model.
     * @param modelUrl The URL to download the model from.
     * @param onDownloadComplete Callback function to be invoked when the download is complete.
     */
    private fun downloadModel(
        modelName: String,
        modelUrl: String,
        onDownloadComplete: (Boolean) -> Unit
    ) {
        val modelFile = File(modelDirectory, modelName)

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
     * Retrieves the list of downloaded models available in the models directory.
     *
     * @return A list of model names corresponding to the downloaded files.
     */
    fun getDownloadedModels(): List<String> {
        val models = mapOf(
            "Europe" to "landmarks-europe.tflite",
            "Asia" to "landmarks-asia.tflite",
            "Africa" to "landmarks-africa.tflite",
            "North America" to "landmarks-north-america.tflite",
            "South America" to "landmarks-south-america.tflite",
            "Oceania" to "landmarks-oceania-antarctica.tflite",
        )

        return modelDirectory.listFiles()
            ?.filter { it.isFile && it.extension == "tflite" } // Filter for .tflite files
            ?.map { file ->
                // Reverse map the filename to the model name
                models.entries.firstOrNull { it.value == file.name }?.key ?: file.name
            } ?: emptyList()
    }
}