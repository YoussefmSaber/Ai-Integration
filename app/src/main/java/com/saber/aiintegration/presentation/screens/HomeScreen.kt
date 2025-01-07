package com.saber.aiintegration.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.saber.aiintegration.R
import com.saber.aiintegration.presentation.componants.GeneralTopBar
import com.saber.aiintegration.presentation.componants.PlaceholderItem
import com.saber.aiintegration.presentation.componants.TakenImageItem
import com.saber.aiintegration.presentation.viewmodels.HomeViewModel
import com.saber.aiintegration.utils.HOME_DESC
import com.saber.aiintegration.utils.HOME_TEXT
import com.saber.aiintegration.utils.byteArrayToBitmap
import com.saber.aiintegration.utils.icons.Iconly
import com.saber.aiintegration.utils.icons.iconly.Camera
import org.koin.androidx.compose.koinViewModel

// TODO: When entering the home screen make sure there is at least one model is available, if not Show an alert dialog to chose a model to download

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onSettingCallBack: () -> Unit,
    onCameraCallBack: () -> Unit,
) {
    val landmarks = viewModel.landmarks.collectAsState()
    Scaffold(
        containerColor = Color.White,
        topBar = {
            GeneralTopBar(
                title = "GeoGlimpse",
                isSettingsIcon = true,
                onCLickCallBack = onSettingCallBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCameraCallBack,
            ) {
                Icon(Iconly.Camera, contentDescription = "Camera Button")
            }
        }
    ) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            if (landmarks.value.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(landmarks.value, key = { it.id }) { landmark ->
                        TakenImageItem(
                            landmarkImage = landmark.imageData.byteArrayToBitmap(),
                            landmarkTitle = landmark.landmarkName,
                        )
                    }
                }
            } else {
                PlaceholderItem(
                    placeholderTitle = HOME_TEXT,
                    R.drawable.no_images,
                    placeholderDescription = HOME_DESC,
                    modifier = Modifier
                )
            }
        }
    }
}