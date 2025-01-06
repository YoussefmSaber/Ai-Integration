package com.saber.aiintegration.presentation.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.saber.aiintegration.data.manager.ModelManager
import com.saber.aiintegration.data.manager.TfLiteLandmarkClassifier
import com.saber.aiintegration.domain.classification.Classification
import com.saber.aiintegration.presentation.componants.CameraPreview
import com.saber.aiintegration.presentation.componants.CurrentModelDropDown
import com.saber.aiintegration.presentation.componants.DetectedLandmarkTitle
import com.saber.aiintegration.presentation.viewmodels.LandmarkClassifierViewModel
import com.saber.aiintegration.utils.LandmarkImageAnalyzer
import com.saber.aiintegration.utils.icons.Iconly
import com.saber.aiintegration.utils.icons.iconly.Camera
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf


@Composable
fun LandmarkClassifierScreen(context: Context = LocalContext.current) {
    // ViewModel and dependencies
    val viewModel: LandmarkClassifierViewModel =
        koinViewModel(parameters = { parametersOf(context) })
    val modelManager: ModelManager = koinInject { parametersOf(context) }

    // Observables
    val availableModels by viewModel.availableModels.collectAsState()
    val selectedModel by viewModel.selectedModel.collectAsState()

    // State for classification results
    var classification by remember { mutableStateOf(emptyList<Classification>()) }

    // Create analyzer and controller
    val analyzer = remember(viewModel, selectedModel) {
        LandmarkImageAnalyzer(
            classifier = TfLiteLandmarkClassifier(context, modelManager),
            location = selectedModel,
            onResults = { results -> classification = results }
        )
    }

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS or CameraController.IMAGE_CAPTURE)
            setImageAnalysisAnalyzer(ContextCompat.getMainExecutor(context), analyzer)
        }
    }

    // Main Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InstructionsText()
        Spacer(Modifier.height(32.dp))
        CameraPreview(controller = controller, modifier = Modifier.cameraPreviewStyle())
        Spacer(Modifier.height(32.dp))
        DetectedLandmarkTitle(classification.firstOrNull()?.label ?: "No landmark detected")
        Spacer(Modifier.height(32.dp))
        ActionRow(
            availableModels = availableModels,
            onSelectModel = { model -> viewModel.selectModel(model) },
            onPhotoClick = {
                takePhoto(controller, context) { bitmap ->
                    viewModel.saveLandmark("Landmark", bitmap)

                }
            }
        )
    }
}

@Composable
private fun InstructionsText() {
    BasicText(text = "Center the object inside the square", style = TextStyle(fontSize = 16.sp))
}

@Composable
private fun Modifier.cameraPreviewStyle(): Modifier = this
    .size(300.dp)
    .clip(RoundedCornerShape(25.dp))

@Composable
private fun ActionRow(
    availableModels: List<String>,
    onSelectModel: (String) -> Unit,
    onPhotoClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        FloatingActionButton(
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
            onClick = onPhotoClick
        ) {
            Icon(Iconly.Camera, contentDescription = "Camera Button")
        }

        Spacer(Modifier.width(16.dp))

        CurrentModelDropDown(
            availableModels = availableModels,
            getCurrentModel = onSelectModel,
            modifier = Modifier.width(150.dp)
        )
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: ", exception)
            }
        }
    )
}