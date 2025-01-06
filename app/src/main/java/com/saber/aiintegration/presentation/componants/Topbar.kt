package com.saber.aiintegration.presentation.componants

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.saber.aiintegration.utils.icons.Iconly
import com.saber.aiintegration.utils.icons.iconly.`Back-arrow`
import com.saber.aiintegration.utils.icons.iconly.Setting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralTopBar(
    title: String,
    isNavigationIcon: Boolean = false,
    isSettingsIcon: Boolean = false,
    onCLickCallBack: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarColors(
            scrolledContainerColor = Transparent,
            titleContentColor = Black,
            actionIconContentColor = Black,
            containerColor = Transparent,
            navigationIconContentColor = Black
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                fontSize = 25.sp,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (isNavigationIcon) {
                IconButton(onClick = onCLickCallBack) {
                    Icon(imageVector = Iconly.`Back-arrow`, contentDescription = "Back arrow icon")
                }
            }
        },
        actions = {
            if (isSettingsIcon) {
                IconButton(onClick = onCLickCallBack) {
                    Icon(imageVector = Iconly.Setting, contentDescription = "Setting Icon")
                }
            }
        }
    )
}