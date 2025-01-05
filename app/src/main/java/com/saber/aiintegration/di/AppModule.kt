package com.saber.aiintegration.di

import android.content.Context
import com.saber.aiintegration.viewmodel.BackgroundRemovalViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parameterSetOf
import org.koin.dsl.module
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.gpu.GpuDelegate
import java.nio.channels.FileChannel

val appModule = module {
    single { (context: Context) -> loadModel(context) }
    viewModel { (context: Context) -> BackgroundRemovalViewModel(get { parameterSetOf(context) }) }


}

private fun loadModel(context: Context): Interpreter {
    val assetFileDescriptor = context.assets.openFd("deeplabv3.tflite")
    val inputStream = assetFileDescriptor.createInputStream()
    val fileChannel = inputStream.channel
    val mappedByteBuffer = fileChannel.map(
        FileChannel.MapMode.READ_ONLY,
        assetFileDescriptor.startOffset,
        assetFileDescriptor.declaredLength
    )
    return Interpreter(mappedByteBuffer, Interpreter.Options().apply {
        addDelegate(GpuDelegate())
    })
}