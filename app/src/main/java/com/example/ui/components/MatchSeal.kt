package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MatchTier
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal500
import com.example.ui.theme.Signal600
import com.example.ui.theme.Signal800
import com.example.ui.theme.TextMuted

/**
 * Signature Component: Match Seal (Spec 3.9)
 * A ring made of five arcs with 4° gaps.
 * Arc lengths proportional to signal weights:
 * Visual 35%, Description 25%, Category & Brand 15%, Location 15%, Time 10%.
 * Arc fills equal to that signal's sub-score.
 */
@Composable
fun MatchSeal(
    score: Int,
    tier: MatchTier = MatchTier.Likely,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    isDarkSurface: Boolean = false,
    visualScore: Int = 91,
    descriptionScore: Int = 88,
    attributesScore: Int = 93,
    locationScore: Int = 95,
    timeScore: Int = 90,
    animate: Boolean = true,
    tierLabel: String = if (tier == MatchTier.Likely) "LIKELY MATCH" else if (tier == MatchTier.Possible) "POSSIBLE" else "LOW CONFIDENCE",
    onClick: (() -> Unit)? = null
) {
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl

    // Animation progress
    val animProgress = remember { Animatable(if (animate) 0f else 1f) }
    val displayScore = remember { Animatable(if (animate) 0f else score.toFloat()) }

    LaunchedEffect(score, animate) {
        if (animate) {
            animProgress.snapTo(0f)
            displayScore.snapTo(0f)
            animProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
            )
            displayScore.animateTo(
                targetValue = score.toFloat(),
                animationSpec = tween(durationMillis = 1100, easing = FastOutSlowInEasing)
            )
        } else {
            animProgress.snapTo(1f)
            displayScore.snapTo(score.toFloat())
        }
    }

    val trackColor = if (isDarkSurface) Navy700 else BorderColor
    val fillColor = when {
        tier == MatchTier.LowConfidence -> if (isDarkSurface) Navy700 else BorderStrong
        isDarkSurface -> Signal500
        else -> Signal600
    }

    val strokeWidth = when {
        size >= 140.dp -> 12.dp
        size >= 100.dp -> 9.dp
        size >= 60.dp -> 6.dp
        else -> 4.dp
    }

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(size)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        // Draw the 5-arc ring
        Canvas(modifier = Modifier.size(size)) {
            val strokePx = strokeWidth.toPx()
            val canvasSize = this.size.minDimension
            val radius = (canvasSize - strokePx) / 2f
            val centerOffset = Offset(this.size.width / 2f, this.size.height / 2f)

            if (size < 44.dp) {
                // Mini 40px: single continuous ring
                drawCircle(
                    color = trackColor,
                    radius = radius,
                    center = centerOffset,
                    style = Stroke(width = strokePx)
                )
                drawArc(
                    color = fillColor,
                    startAngle = -90f,
                    sweepAngle = 360f * (score / 100f) * animProgress.value * (if (isRtl) -1f else 1f),
                    useCenter = false,
                    topLeft = Offset(centerOffset.x - radius, centerOffset.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = strokePx, cap = StrokeCap.Round)
                )
            } else {
                // Full 5-arc ring with 4-deg gaps
                // Weights: 35%, 25%, 15%, 15%, 10%
                val weights = listOf(0.35f, 0.25f, 0.15f, 0.15f, 0.10f)
                val subScores = listOf(
                    visualScore / 100f,
                    descriptionScore / 100f,
                    attributesScore / 100f,
                    locationScore / 100f,
                    timeScore / 100f
                )

                val gapDeg = 4f
                val totalAvailableDeg = 360f - (gapDeg * weights.size)
                var currentAngle = -90f // Start at 12 o'clock

                for (i in weights.indices) {
                    val arcWeight = weights[i]
                    val arcMaxSweep = totalAvailableDeg * arcWeight
                    val subScore = subScores[i]
                    val effectiveSweep = arcMaxSweep * subScore * animProgress.value

                    val directionMultiplier = if (isRtl) -1f else 1f
                    val arcStart = currentAngle

                    // Draw track
                    drawArc(
                        color = trackColor,
                        startAngle = arcStart,
                        sweepAngle = arcMaxSweep * directionMultiplier,
                        useCenter = false,
                        topLeft = Offset(centerOffset.x - radius, centerOffset.y - radius),
                        size = Size(radius * 2, radius * 2),
                        style = Stroke(width = strokePx, cap = StrokeCap.Round)
                    )

                    // Draw active filled portion
                    if (effectiveSweep > 0.5f) {
                        drawArc(
                            color = fillColor,
                            startAngle = arcStart,
                            sweepAngle = effectiveSweep * directionMultiplier,
                            useCenter = false,
                            topLeft = Offset(centerOffset.x - radius, centerOffset.y - radius),
                            size = Size(radius * 2, radius * 2),
                            style = Stroke(width = strokePx, cap = StrokeCap.Round)
                        )
                    }

                    currentAngle += (arcMaxSweep + gapDeg) * directionMultiplier
                }
            }
        }

        // Center Numerals and Tier Label
        val scoreNumber = displayScore.value.toInt()
        val textColor = when {
            tier == MatchTier.LowConfidence -> TextMuted
            isDarkSurface -> Color.White
            else -> Navy900
        }

        val tierColor = when {
            tier == MatchTier.LowConfidence -> TextMuted
            isDarkSurface -> Signal500
            else -> Signal800
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "$scoreNumber",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = when {
                        size >= 140.dp -> 64.sp
                        size >= 100.dp -> 44.sp
                        size >= 60.dp -> 22.sp
                        else -> 14.sp
                    },
                    color = textColor,
                    lineHeight = when {
                        size >= 140.dp -> 64.sp
                        size >= 100.dp -> 44.sp
                        size >= 60.dp -> 22.sp
                        else -> 14.sp
                    }
                )
                Text(
                    text = "%",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = when {
                        size >= 140.dp -> 20.sp
                        size >= 100.dp -> 15.sp
                        size >= 60.dp -> 11.sp
                        else -> 8.sp
                    },
                    color = tierColor
                )
            }

            if (size >= 100.dp && tierLabel.isNotEmpty()) {
                Text(
                    text = tierLabel,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (size >= 140.dp) 11.sp else 9.sp,
                    letterSpacing = 0.5.sp,
                    color = tierColor
                )
            }
        }
    }
}
