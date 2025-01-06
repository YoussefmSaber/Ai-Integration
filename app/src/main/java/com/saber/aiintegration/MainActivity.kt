package com.saber.aiintegration

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.saber.aiintegration.data.manager.TfLiteLandmarkClassifier
import com.saber.aiintegration.domain.Classification
import com.saber.aiintegration.presentation.componants.CameraPreview
import com.saber.aiintegration.utils.LandmarkImageAnalyzer
import com.saber.aiintegration.ui.theme.AiIntegrationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (!hasCameraPermission()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), 0
            )
        }
        setContent {
            AiIntegrationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    var classification by remember {
//                        mutableStateOf(emptyList<Classification>())
//                    }
//                    val analyzer = remember {
//                        LandmarkImageAnalyzer(
//                            classifier = TfLiteLandmarkClassifier(context = applicationContext),
//                            location = "Europe",
//                            onResults = {
//                                classification = it
//                            }
//                        )
//                    }
//                    val controller = remember {
//                        LifecycleCameraController(applicationContext).apply {
//                            setEnabledUseCases(CameraController.IMAGE_ANALYSIS or CameraController.IMAGE_CAPTURE)
//                            setImageAnalysisAnalyzer(
//                                ContextCompat.getMainExecutor(applicationContext),
//                                analyzer
//                            )
//                        }
//                    }
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding)
//                    ) {
//                        CameraPreview(
//                            controller,
//                            Modifier
//                                .width(300.dp)
//                                .height(300.dp)
//                                .clip(RoundedCornerShape(25.dp))
//
//                        )
//
//                        Column(Modifier.fillMaxWidth()) {
//                            classification.forEach {
//                                Text("${it.label} - ${it.score}")
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

