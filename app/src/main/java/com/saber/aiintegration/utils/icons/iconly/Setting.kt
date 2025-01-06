package com.saber.aiintegration.utils.icons.iconly

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

public val Iconly.Setting: ImageVector
    get() {
        if (_setting != null) {
            return _setting!!
        }
        _setting = Builder(name = "Setting", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.4f, strokeAlpha
                    = 0.4f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(19.7639f, 12.914f)
                curveTo(19.4369f, 12.723f, 19.2409f, 12.382f, 19.2409f, 12.0f)
                curveTo(19.2409f, 11.618f, 19.4369f, 11.277f, 19.7649f, 11.087f)
                curveTo(21.1829f, 10.262f, 21.6699f, 8.43f, 20.8519f, 7.001f)
                curveTo(20.4529f, 6.307f, 19.8099f, 5.811f, 19.0399f, 5.604f)
                curveTo(18.2739f, 5.401f, 17.4739f, 5.507f, 16.7879f, 5.906f)
                curveTo(16.4639f, 6.096f, 16.0769f, 6.096f, 15.7509f, 5.907f)
                curveTo(15.4209f, 5.718f, 15.2239f, 5.375f, 15.2239f, 4.992f)
                curveTo(15.2239f, 3.343f, 13.8899f, 2.0f, 12.2499f, 2.0f)
                curveTo(10.6089f, 2.0f, 9.2749f, 3.343f, 9.2749f, 4.992f)
                curveTo(9.2749f, 5.376f, 9.0779f, 5.718f, 8.7479f, 5.908f)
                curveTo(8.4239f, 6.094f, 8.0369f, 6.096f, 7.7129f, 5.906f)
                curveTo(7.0249f, 5.507f, 6.2259f, 5.399f, 5.4579f, 5.605f)
                curveTo(4.6879f, 5.812f, 4.0459f, 6.308f, 3.6479f, 7.002f)
                curveTo(2.8299f, 8.431f, 3.3179f, 10.263f, 4.7359f, 11.086f)
                curveTo(5.0629f, 11.276f, 5.2579f, 11.618f, 5.2579f, 12.0f)
                curveTo(5.2579f, 12.382f, 5.0629f, 12.724f, 4.7359f, 12.913f)
                curveTo(3.3179f, 13.738f, 2.8299f, 15.571f, 3.6479f, 16.999f)
                curveTo(4.0459f, 17.692f, 4.6879f, 18.188f, 5.4569f, 18.395f)
                curveTo(6.2239f, 18.6f, 7.0249f, 18.494f, 7.7129f, 18.095f)
                curveTo(8.0359f, 17.905f, 8.4229f, 17.905f, 8.7479f, 18.092f)
                curveTo(9.0779f, 18.282f, 9.2749f, 18.624f, 9.2749f, 19.008f)
                curveTo(9.2749f, 20.657f, 10.6089f, 22.0f, 12.2499f, 22.0f)
                curveTo(13.8899f, 22.0f, 15.2239f, 20.657f, 15.2239f, 19.008f)
                curveTo(15.2239f, 18.625f, 15.4209f, 18.282f, 15.7509f, 18.093f)
                curveTo(16.0759f, 17.905f, 16.4629f, 17.905f, 16.7879f, 18.095f)
                curveTo(17.4749f, 18.494f, 18.2749f, 18.598f, 19.0399f, 18.396f)
                curveTo(19.8099f, 18.189f, 20.4529f, 17.693f, 20.8519f, 16.999f)
                curveTo(21.6699f, 15.571f, 21.1829f, 13.739f, 19.7639f, 12.914f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(9.2498f, 12.0f)
                curveTo(9.2498f, 13.654f, 10.5958f, 15.0f, 12.2498f, 15.0f)
                curveTo(13.9038f, 15.0f, 15.2498f, 13.654f, 15.2498f, 12.0f)
                curveTo(15.2498f, 10.346f, 13.9038f, 9.0f, 12.2498f, 9.0f)
                curveTo(10.5958f, 9.0f, 9.2498f, 10.346f, 9.2498f, 12.0f)
                close()
            }
        }
        .build()
        return _setting!!
    }

private var _setting: ImageVector? = null
