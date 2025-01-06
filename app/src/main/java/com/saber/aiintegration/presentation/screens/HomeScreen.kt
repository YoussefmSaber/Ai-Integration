package com.saber.aiintegration.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saber.aiintegration.R
import com.saber.aiintegration.presentation.componants.GeneralTopBar
import com.saber.aiintegration.presentation.componants.PlaceholderItem
import com.saber.aiintegration.utils.HOME_DESC
import com.saber.aiintegration.utils.HOME_TEXT

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            GeneralTopBar(
                title = "GeoGlimpse",
                isSettingsIcon = true,
                onCLickCallBack = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {

            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            PlaceholderItem(
                placeholderImage = R.drawable.no_images,
                placeholderTitle = HOME_TEXT,
                placeholderDescription = HOME_DESC,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}