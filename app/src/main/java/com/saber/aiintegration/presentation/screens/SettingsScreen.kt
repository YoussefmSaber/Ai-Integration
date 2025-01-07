package com.saber.aiintegration.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.saber.aiintegration.presentation.componants.GeneralTopBar
import com.saber.aiintegration.presentation.componants.ModelSwitch
import com.saber.aiintegration.presentation.viewmodels.SettingsViewModel
import com.saber.aiintegration.utils.MODELS
import com.saber.aiintegration.utils.REGION_LIST
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SettingsScreen(
    context: Context = LocalContext.current,
    onBack: () -> Unit
) {
    val viewModel: SettingsViewModel = koinViewModel(parameters = { parametersOf(context) })
    val availableModels by viewModel.availableModels.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val dialogType by viewModel.dialogType.collectAsState()

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
                ModelSwitch(modelName = modelName,
                    isChecked = isModelAvailable,
                    onCheckedChange = { checked ->
                        if (checked) {
                            viewModel.showDownloadDialog() // Show the download dialog
                        } else {
                            viewModel.showRemoveDialog() // Show the remove dialog
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
            Text(
                text = if (dialogType == SettingsViewModel.DialogType.REMOVE)
                    "You are going to remove the model. Are you sure about that?"
                else
                    "You are going to download the model. Are you sure about that?"
            )
        }, onDismissRequest = {
            viewModel.dismissDialog()
        }, confirmButton = {
            TextButton(onClick = {
                if (dialogType == SettingsViewModel.DialogType.REMOVE) {
                    // Call function to remove the model here
                } else {
                    // Call function to download the model here
                }
                viewModel.dismissDialog()
            }) {
                Text("Confirm")
            }
        }, dismissButton = {
            TextButton(onClick = {
                viewModel.dismissDialog()
            }) {
                Text("Dismiss")
            }
        })
    }
}