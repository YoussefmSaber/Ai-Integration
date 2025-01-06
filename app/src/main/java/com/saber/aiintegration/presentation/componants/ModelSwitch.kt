package com.saber.aiintegration.presentation.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModelSwitch(
    modelName: String,
    isChecked: MutableState<Boolean>,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Text(modelName)
        Switch(
            checked = isChecked.value,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(15.dp)
        )
    }
}