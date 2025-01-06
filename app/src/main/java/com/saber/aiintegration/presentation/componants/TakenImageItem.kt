package com.saber.aiintegration.presentation.componants

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.saber.aiintegration.utils.fadingEdge

@Composable
fun TakenImageItem(
    landmarkImage: Bitmap,
    landmarkTitle: String
) {
    ConstraintLayout(
        modifier = Modifier
            .size(200.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(25.dp), color = Color(0xFF000000))
    ) {
        val (image, title) = createRefs()
        Image(
            bitmap = landmarkImage.asImageBitmap(),
            contentDescription = "Landmark Image",
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .fadingEdge(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(landmarkTitle, color = Color.White, modifier = Modifier.constrainAs(title) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
        })
    }
}