package com.saber.aiintegration.data

import android.content.Context
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.IOException

class ModelManager(private val context: Context) {

    private val modelDirectory = File(context.filesDir, "models")

    init {
        if (!modelDirectory.exists()) {
            modelDirectory.mkdir()
        }
    }

    fun getModelPath(modelName: String): File? {
        val modelFile = File(modelDirectory, modelName)
        return if (modelFile.exists()) modelFile else null
    }

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

    fun clearUnusedModels(keepModels: List<String>) {
        modelDirectory.listFiles()?.forEach { file ->
            if (!keepModels.contains(file.nameWithoutExtension)) {
                file.delete()
            }
        }
    }
}