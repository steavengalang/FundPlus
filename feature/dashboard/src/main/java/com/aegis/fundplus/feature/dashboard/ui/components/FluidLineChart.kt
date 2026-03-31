package com.aegis.fundplus.feature.dashboard.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun FluidLineChart(
    dataPoints: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFF1E88E5), // Elegan Blue
    gradientStartColor: Color = Color(0x661E88E5), // Semitransparent Blue
    gradientEndColor: Color = Color(0x001E88E5) // Completely transparent
) {
    if (dataPoints.isEmpty()) return

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val width = size.width
        val height = size.height
        val maxData = dataPoints.maxOrNull() ?: 1f
        val minData = dataPoints.minOrNull() ?: 0f
        val range = if (maxData == minData) 1f else maxData - minData

        // Hitung jarak X antar titik
        val xStep = width / (dataPoints.size - 1).coerceAtLeast(1)

        val path = Path()
        val fillPath = Path()

        var prevX = 0f
        var prevY = height - ((dataPoints.first() - minData) / range * height)

        path.moveTo(prevX, prevY)
        fillPath.moveTo(prevX, height)
        fillPath.lineTo(prevX, prevY)

        for (i in 1 until dataPoints.size) {
            val nextX = i * xStep
            val nextY = height - ((dataPoints[i] - minData) / range * height)

            // Menggambar garis lurus (Bisa juga memakai kurva / Bezier/CubicTo untuk lebih smooth)
            // Untuk gaya Apple Wallet, kita gunakan CubicTo membulat (Smooth Curve)
            val controlPoint1X = prevX + (nextX - prevX) / 2f
            val controlPoint1Y = prevY
            val controlPoint2X = prevX + (nextX - prevX) / 2f
            val controlPoint2Y = nextY

            path.cubicTo(
                controlPoint1X, controlPoint1Y,
                controlPoint2X, controlPoint2Y,
                nextX, nextY
            )
            
            fillPath.cubicTo(
                controlPoint1X, controlPoint1Y,
                controlPoint2X, controlPoint2Y,
                nextX, nextY
            )

            prevX = nextX
            prevY = nextY
        }

        // Menutup rute fill bayangan gradient
        fillPath.lineTo(prevX, height)
        fillPath.close()

        // 1. Gambar Gradient Isi dari Kaca (Frost Glass Effect)
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(gradientStartColor, gradientEndColor),
                startY = 0f,
                endY = height
            )
        )

        // 2. Gambar Garis Curva Tebal (Minimalis, Tanpa Grid X dan Y)
        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 6.dp.toPx()) // Tebal eksklusif CEO Vibe
        )
    }
}
