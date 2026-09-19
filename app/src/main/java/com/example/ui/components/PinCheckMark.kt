package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.Signal500
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal600

/**
 * MAFQOOD Brand Pin-Check Mark (Spec 1.1)
 * A pin silhouette with an inner checkmark that draws smoothly.
 */
@Composable
fun PinCheckMark(
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
    pinColor: Color = Color.White,
    checkColor: Color = Teal600,
    ringColor: Color? = null,
    animate: Boolean = true
) {
    val checkProgress = remember { Animatable(if (animate) 0f else 1f) }
    val ringProgress = remember { Animatable(if (animate) 0f else 1f) }

    LaunchedEffect(animate) {
        if (animate) {
            checkProgress.snapTo(0f)
            ringProgress.snapTo(0f)
            ringProgress.animateTo(1f, tween(400, easing = FastOutSlowInEasing))
            checkProgress.animateTo(1f, tween(500, easing = FastOutSlowInEasing))
        }
    }

    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height

        // Optional outer circle ring
        if (ringColor != null) {
            val r = (w.coerceAtMost(h) / 2f) - 4f
            drawCircle(
                color = ringColor.copy(alpha = 0.2f),
                radius = r,
                center = Offset(w / 2f, h / 2f),
                style = Stroke(width = 3.dp.toPx())
            )
            if (ringProgress.value > 0f) {
                drawArc(
                    color = ringColor,
                    startAngle = -90f,
                    sweepAngle = 360f * ringProgress.value,
                    useCenter = false,
                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }

        // Draw Map Pin outline/silhouette
        val pinPath = Path().apply {
            val centerX = w * 0.5f
            val topY = h * 0.22f
            val pinRadius = w * 0.26f

            // Pin head circle
            moveTo(centerX, topY)
            // Arc or pin geometry
            cubicTo(
                centerX + pinRadius * 1.3f, topY,
                centerX + pinRadius * 1.3f, topY + pinRadius * 1.2f,
                centerX, h * 0.78f
            )
            cubicTo(
                centerX - pinRadius * 1.3f, topY + pinRadius * 1.2f,
                centerX - pinRadius * 1.3f, topY,
                centerX, topY
            )
            close()
        }

        drawPath(
            path = pinPath,
            color = pinColor
        )

        // Draw animated Check Mark inside the pin head
        val checkPath = Path().apply {
            val startX = w * 0.38f
            val startY = h * 0.40f
            val midX = w * 0.47f
            val midY = h * 0.49f
            val endX = w * 0.63f
            val endY = h * 0.33f

            moveTo(startX, startY)
            lineTo(midX, midY)
            lineTo(endX, endY)
        }

        val pathMeasure = PathMeasure()
        pathMeasure.setPath(checkPath, false)
        val length = pathMeasure.length
        val animatedPath = Path()
        pathMeasure.getSegment(0f, length * checkProgress.value, animatedPath, true)

        drawPath(
            path = animatedPath,
            color = checkColor,
            style = Stroke(
                width = (w * 0.08f).coerceAtLeast(2.5f),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
