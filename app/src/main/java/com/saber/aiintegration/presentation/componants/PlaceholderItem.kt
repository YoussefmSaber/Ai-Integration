package com.saber.aiintegration.presentation.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PlaceholderItem(
    placeholderTitle: String,
    placeholderImage: Int,
    placeholderDescription: String,
    modifier: Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(placeholderImage),
            contentDescription = placeholderDescription
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            placeholderTitle,
            textAlign = TextAlign.Center
        )
    }
}