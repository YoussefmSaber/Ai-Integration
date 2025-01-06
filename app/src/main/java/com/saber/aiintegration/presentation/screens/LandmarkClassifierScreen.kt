package com.saber.aiintegration.presentation.screens

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.saber.aiintegration.data.manager.ModelManager
import com.saber.aiintegration.data.manager.TfLiteLandmarkClassifier
import com.saber.aiintegration.domain.Classification
import com.saber.aiintegration.presentation.componants.CameraPreview
import com.saber.aiintegration.presentation.componants.CurrentModelDropDown
import com.saber.aiintegration.presentation.componants.DetectedLandmarkTitle
import com.saber.aiintegration.utils.LandmarkImageAnalyzer
import com.saber.aiintegration.utils.icons.Iconly
import com.saber.aiintegration.utils.icons.iconly.Camera


@Composable
fun LandmarkClassifierScreen(applicationContext: Context) {
    // State for classification results
    var classification by remember { mutableStateOf(emptyList<Classification>()) }

    // State for selected model
    var selectedModel by remember { mutableStateOf("") }

    // Initialize ModelManager
    val modelManager = remember { ModelManager(applicationContext) }

    // State for available models
    var availableModels by remember { mutableStateOf(emptyList<String>()) }

    // Retrieve downloaded models
    LaunchedEffect(Unit) {
        availableModels = modelManager.getDownloadedModels()
        if (availableModels.isNotEmpty()) {
            selectedModel = availableModels.first() // Default to the first available model
        }
    }

    // Create the analyzer with the selected model
    val analyzer = remember(applicationContext, selectedModel) {
        LandmarkImageAnalyzer(
            classifier = TfLiteLandmarkClassifier(
                context = applicationContext,
                modelManager
            ),
            location = selectedModel, // Pass the selected model here
            onResults = { results ->
                classification = results
            }
        )
    }

    val controller = remember {
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS or CameraController.IMAGE_CAPTURE)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(applicationContext),
                analyzer
            )
        }
    }

    // UI Layout
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        BasicText(
            text = "Center the object inside the square",
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(Modifier.height(64.dp))

        // Camera Preview
        CameraPreview(
            controller = controller,
            modifier = Modifier
                .height(350.dp)
                .width(350.dp)
                .clip(RoundedCornerShape(25.dp))
        )

        Spacer(Modifier.height(32.dp))

        // Detected Landmark Title
        val landmark = classification.firstOrNull()?.label ?: "No landmark detected"
        DetectedLandmarkTitle(landmark)

        Spacer(Modifier.height(32.dp))

        // Floating Action Button and Dropdown Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
                    defaultElevation = 0.dp
                ),
                shape = CircleShape,
                onClick = {
                    // Optional: Trigger additional functionality, like capturing an image
                }
            ) {
                Icon(Iconly.Camera, contentDescription = "Camera Button")
            }

            Spacer(Modifier.width(16.dp))

            // Model Dropdown
            CurrentModelDropDown(
                availableModels = availableModels,
                getCurrentModel = { model -> selectedModel = model },
                modifier = Modifier.width(150.dp)
            )
        }
    }
}

