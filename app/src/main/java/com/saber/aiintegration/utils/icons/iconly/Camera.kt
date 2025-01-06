package com.saber.aiintegration.utils.icons.iconly

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saber.aiintegration.utils.icons.Iconly
import kotlin.Unit

public val Iconly.Camera: ImageVector
    get() {
        if (_camera != null) {
            return _camera!!
        }
        _camera = Builder(name = "Camera", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(15.04f, 4.051f)
                curveTo(16.05f, 4.453f, 16.359f, 5.853f, 16.772f, 6.303f)
                curveTo(17.185f, 6.753f, 17.776f, 6.906f, 18.103f, 6.906f)
                curveTo(19.841f, 6.906f, 21.25f, 8.315f, 21.25f, 10.052f)
                lineTo(21.25f, 15.847f)
                curveTo(21.25f, 18.177f, 19.36f, 20.067f, 17.03f, 20.067f)
                lineTo(6.97f, 20.067f)
                curveTo(4.639f, 20.067f, 2.75f, 18.177f, 2.75f, 15.847f)
                lineTo(2.75f, 10.052f)
                curveTo(2.75f, 8.315f, 4.159f, 6.906f, 5.897f, 6.906f)
                curveTo(6.223f, 6.906f, 6.814f, 6.753f, 7.228f, 6.303f)
                curveTo(7.641f, 5.853f, 7.949f, 4.453f, 8.959f, 4.051f)
                curveTo(9.97f, 3.649f, 14.03f, 3.649f, 15.04f, 4.051f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(17.495f, 9.5f)
                lineTo(17.505f, 9.5f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(15.179f, 13.128f)
                curveTo(15.179f, 11.372f, 13.756f, 9.949f, 12.0f, 9.949f)
                curveTo(10.244f, 9.949f, 8.821f, 11.372f, 8.821f, 13.128f)
                curveTo(8.821f, 14.884f, 10.244f, 16.307f, 12.0f, 16.307f)
                curveTo(13.756f, 16.307f, 15.179f, 14.884f, 15.179f, 13.128f)
                close()
            }
        }
        .build()
        return _camera!!
    }

private var _camera: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Iconly.Camera, contentDescription = "")
    }
}
