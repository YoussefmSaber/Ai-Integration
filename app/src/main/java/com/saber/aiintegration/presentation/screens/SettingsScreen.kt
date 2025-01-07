package com.saber.aiintegration.presentation.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.saber.aiintegration.presentation.componants.GeneralTopBar
import com.saber.aiintegration.presentation.componants.ModelSwitch
import com.saber.aiintegration.presentation.viewmodels.SettingsViewModel
import com.saber.aiintegration.utils.REGION_LIST
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

//TODO: Implement the download function with a progress bar

@Composable
fun SettingsScreen(
    context: Context = LocalContext.current,
    onBack: () -> Unit
) {
    val viewModel: SettingsViewModel = koinViewModel(parameters = { parametersOf(context) })
    val availableModels by viewModel.availableModels.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val dialogType by viewModel.dialogType.collectAsState()
    val selectedModel by viewModel.selectedModel.collectAsState()
    val modelDownloadProgress by viewModel.modelDownloadProgress.collectAsState(initial = 0f)
    val startDownload = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        GeneralTopBar(title = "Settings", isNavigationIcon = true, onCLickCallBack = onBack)
    }) { innerPadding ->
        LazyColumn(Modifier.padding(innerPadding)) {
            item {
                Text("Models")
            }
            items(REGION_LIST) { modelName ->
                val isModelAvailable = remember {
                    mutableStateOf(availableModels.contains(modelName))
                }
                Log.d("Settings", "SettingsScreen: $availableModels")
                ModelSwitch(modelName = modelName,
                    isChecked = isModelAvailable,
                    onCheckedChange = { checked ->
                        if (checked) {
                            viewModel.showDownloadDialog(modelName) // Show the download dialog
                        } else {
                            viewModel.showRemoveDialog(modelName) // Show the remove dialog
                        }
                    })
            }
        }
    }

    if (showDialog) {
        AlertDialog(title = {
            Text(
                text = if (dialogType == SettingsViewModel.DialogType.REMOVE)
                    "Model Removal"
                else
                    "Model Download"
            )
        }, text = {
            if (dialogType == SettingsViewModel.DialogType.DOWNLOAD) {
                if (startDownload.value)
                    Column {
                        Text("Downloading: ${modelDownloadProgress.toInt()}%")
                        Spacer(Modifier.height(16.dp))
                        LinearProgressIndicator(
                            progress = { modelDownloadProgress / 100 },
                        )
                        if (modelDownloadProgress == 100f) {
                            startDownload.value = false
                            viewModel.dismissDialog()
                        }
                    }
                else
                    Text("You are going to download the model. Are you sure about that?")
            } else {
                Text("You are going to remove the model. Are you sure about that?")
            }
        }, onDismissRequest = {
            viewModel.dismissDialog()
        }, confirmButton = {
            if (!startDownload.value) {
                TextButton(onClick = {
                    if (dialogType == SettingsViewModel.DialogType.REMOVE) {
//                    selectedModel?.let { viewModel. }
                    } else {
                        startDownload.value = true
                        selectedModel?.let { viewModel.downloadModel(it) }
                    }
                }) {
                    Text("Confirm")
                }
            }
        }, dismissButton = {
            if (!startDownload.value) {
                TextButton(onClick = {
                    viewModel.dismissDialog()
                }) {
                    Text("Dismiss")
                }
            }
        })
    }
}