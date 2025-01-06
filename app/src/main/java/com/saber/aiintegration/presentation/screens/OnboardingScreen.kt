package com.saber.aiintegration.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.saber.aiintegration.R
import com.saber.aiintegration.presentation.componants.PlaceholderItem
import com.saber.aiintegration.utils.ONBOARDING_DESC
import com.saber.aiintegration.utils.ONBOARDING_TEXT


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreen(
    onClickNavigate: () -> Unit = {}
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (content, button) = createRefs()

        PlaceholderItem(
            ONBOARDING_TEXT,
            R.drawable.onboarding,
            ONBOARDING_DESC,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Button(
            shape = RoundedCornerShape(12.dp),
            onClick = onClickNavigate,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            BasicText(
                "Start Discovering",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
