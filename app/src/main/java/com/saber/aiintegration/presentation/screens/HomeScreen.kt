package com.saber.aiintegration.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.saber.aiintegration.R
import com.saber.aiintegration.presentation.componants.GeneralTopBar
import com.saber.aiintegration.presentation.componants.PlaceholderItem
import com.saber.aiintegration.utils.HOME_DESC
import com.saber.aiintegration.utils.HOME_TEXT
import com.saber.aiintegration.utils.icons.Iconly
import com.saber.aiintegration.utils.icons.iconly.Camera

@Composable
fun HomeScreen() {

    Scaffold(
        containerColor = Color.White,
        topBar = {
            GeneralTopBar(
                title = "GeoGlimpse",
                isSettingsIcon = true,
                onCLickCallBack = {},
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
            ) {
                Icon(Iconly.Camera, contentDescription = "Camera Button")
            }
        }
    ) { innerPadding ->

        LazyColumn(Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            item {
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