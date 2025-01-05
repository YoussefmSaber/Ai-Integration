package com.saber.aiintegration.di

import android.content.Context
import com.saber.aiintegration.viewmodel.BackgroundRemovalViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parameterSetOf
import org.koin.dsl.module
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.gpu.GpuDelegate
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

val appModule = module {
    single { (context: Context) -> loadModel(context, ) }
    viewModel { (context: Context) -> BackgroundRemovalViewModel(get { parameterSetOf(context) }) }
}

// Function to load the TensorFlow Lite model from the assets folder
private fun loadModel(context: Context): Interpreter? {
    // Declare an Interpreter variable to hold the model
    var interpreter: Interpreter? = null
    try {
        // Open the model file from the assets folder
        context.assets.openFd("deeplabv3.tflite").use { assetFileDescriptor ->
            // Create an input stream from the asset file descriptor
            assetFileDescriptor.createInputStream().use { inputStream ->
                // Get the file channel from the input stream
                val fileChannel = inputStream.channel
                // Map the file into memory
                val mappedByteBuffer: MappedByteBuffer = fileChannel.map(
                    FileChannel.MapMode.READ_ONLY,
                    assetFileDescriptor.startOffset,
                    assetFileDescriptor.declaredLength
                )

                // Set up the interpreter options
                val options = Interpreter.Options().apply {
                    // Check if the device supports GPU delegate
                    val compatList = CompatibilityList()
                    if (compatList.isDelegateSupportedOnThisDevice) {
                        // Add GPU delegate if supported
                        addDelegate(GpuDelegate())
                    }
                }
                // Create the interpreter with the mapped model and options
                interpreter = Interpreter(mappedByteBuffer, options)
            }
        }
    } catch (e: IOException) {
        // Print the stack trace if an IOException occurs
        e.printStackTrace()
        // Return null if an error occurs
        return null
    }
    // Return the interpreter instance
    return interpreter
}