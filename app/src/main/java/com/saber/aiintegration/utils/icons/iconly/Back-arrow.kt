package com.saber.aiintegration.utils.icons.iconly

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.saber.aiintegration.utils.icons.Iconly

public val Iconly.`Back-arrow`: ImageVector
    get() {
        if (`_back-arrow` != null) {
            return `_back-arrow`!!
        }
        `_back-arrow` = Builder(name = "Back-arrow", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.7857f, 10.8672f)
                lineTo(9.9376f, 15.0865f)
                curveTo(9.7842f, 15.1805f, 9.5878f, 15.1715f, 9.4442f, 15.0605f)
                curveTo(8.7936f, 14.5594f, 8.2123f, 14.0514f, 7.8245f, 13.6244f)
                curveTo(7.8245f, 13.6244f, 7.4904f, 13.2824f, 7.3458f, 13.0654f)
                curveTo(7.1123f, 12.7693f, 7.0f, 12.3823f, 7.0f, 12.0063f)
                curveTo(7.0f, 11.5843f, 7.1231f, 11.1853f, 7.3683f, 10.8662f)
                curveTo(7.424f, 10.8092f, 7.636f, 10.5582f, 7.8362f, 10.3532f)
                curveTo(9.0046f, 9.0772f, 12.0545f, 6.969f, 13.6586f, 6.33f)
                curveTo(13.8921f, 6.227f, 14.5153f, 6.012f, 14.8387f, 6.0f)
                curveTo(15.1503f, 6.0f, 15.4512f, 6.068f, 15.7404f, 6.217f)
                curveTo(16.096f, 6.422f, 16.3744f, 6.74f, 16.5307f, 7.1171f)
                curveTo(16.6313f, 7.3791f, 16.7866f, 8.1651f, 16.7866f, 8.1881f)
                curveTo(16.8873f, 8.7491f, 16.9625f, 9.5372f, 16.9996f, 10.4572f)
                curveTo(17.0064f, 10.6222f, 16.9234f, 10.7822f, 16.7857f, 10.8672f)
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                    fillAlpha = 0.4f, strokeAlpha = 0.4f, strokeLineWidth = 1.0f, strokeLineCap =
                    Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.3277f, 13.1398f)
                curveTo(16.6296f, 12.9527f, 17.0096f, 13.1918f, 16.9949f, 13.5508f)
                curveTo(16.9588f, 14.3928f, 16.8963f, 15.1349f, 16.8201f, 15.6869f)
                curveTo(16.8083f, 15.6989f, 16.653f, 16.6779f, 16.4742f, 17.0089f)
                curveTo(16.1626f, 17.624f, 15.5511f, 18.0f, 14.8936f, 18.0f)
                lineTo(14.8389f, 18.0f)
                curveTo(14.4149f, 17.989f, 13.5132f, 17.613f, 13.5132f, 17.59f)
                curveTo(13.059f, 17.401f, 12.466f, 17.081f, 11.8281f, 16.6959f)
                curveTo(11.5409f, 16.5219f, 11.534f, 16.0949f, 11.8212f, 15.9179f)
                lineTo(16.3277f, 13.1398f)
                close()
            }
        }
        .build()
        return `_back-arrow`!!
    }

private var `_back-arrow`: ImageVector? = null
