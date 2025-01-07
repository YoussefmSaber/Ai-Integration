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
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onCameraCallBack: () -> Unit,
) {
    val landmarks = viewModel.landmarks.collectAsState()
    Scaffold(
        containerColor = Color.White,
        topBar = {
            GeneralTopBar(
                title = "GeoGlimpse",
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

        ConstraintLayout(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            val (grid, placeholder) = createRefs()
            if (landmarks.value.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .constrainAs(grid) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
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
                    modifier = Modifier.constrainAs(placeholder) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )
            }
        }
    }
}