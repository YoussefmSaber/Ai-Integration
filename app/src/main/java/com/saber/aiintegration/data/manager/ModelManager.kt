package com.saber.aiintegration.data.manager

import android.content.Context
import com.saber.aiintegration.data.ProgressResponseBody
import com.saber.aiintegration.utils.MODELS
import com.saber.aiintegration.utils.MODELS_URL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.IOException
import okhttp3.Callback


/**
 * Manages the models stored in the application's file directory.
 *
 * @property context The application context.
 */
class ModelManager(private val context: Context) {

    private val modelDirectory = File(context.filesDir, "models")
    private val _downloadProgress = MutableStateFlow(0f) // Progress in percentage
    val downloadProgress: StateFlow<Float> = _downloadProgress

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
        onDownloadComplete: (Boolean, File?) -> Unit
    ) {
        val modelFile = MODELS[modelName]?.let { File(modelDirectory, it) }
        val modelDownloadUrl = MODELS_URL[modelName]
        if (modelFile?.exists() == true) {
            onDownloadComplete(true, modelFile)
            return
        }

        downloadModel(modelName, modelDownloadUrl!!) { success ->
            onDownloadComplete(success, if (success) modelFile else null)
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
        val modelFile = MODELS[modelName]?.let { File(modelDirectory, it) }
        val request = Request.Builder().url(modelUrl).build()
        val okhttpClient = OkHttpClient()

        okhttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _downloadProgress.value = 0f
                onDownloadComplete(false)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    _downloadProgress.value = 0f
                    onDownloadComplete(false)
                    return
                }

                val responseBody = response.body ?: return
                val progressResponseBody =
                    ProgressResponseBody(responseBody) { bytesRead, contentLength ->
                        if (contentLength > 0) { // Prevent division by zero
                            _downloadProgress.value = (bytesRead.toFloat() / contentLength) * 100
                        }
                    }

                modelFile?.outputStream()?.use { output ->
                    progressResponseBody.byteStream().copyTo(output)
                }

                _downloadProgress.value = 100f
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
        return modelDirectory.listFiles()
            ?.filter { it.isFile && it.extension == "tflite" } // Filter for .tflite files
            ?.map { file ->
                // Reverse map the filename to the model name
                MODELS.entries.firstOrNull { it.value == file.name }?.key ?: file.name
            } ?: emptyList()
    }
}