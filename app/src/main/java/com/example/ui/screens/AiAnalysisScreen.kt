package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.AccentDarkButton
import com.example.ui.components.MatchSeal
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.Indigo800
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal500
import com.example.ui.theme.Teal600
import kotlinx.coroutines.delay

@Composable
fun AiAnalysisScreen(
    onSeeMatches: () -> Unit,
    onBackgroundClick: () -> Unit
) {
    val lang = LocalAppLanguage.current

    // 3 phases: 0 = Analyzing, 1 = Searching, 2 = Results Ready
    var phase by remember { mutableIntStateOf(0) }
    var completedStageIndex by remember { mutableIntStateOf(0) }

    // Scan sweep line animation
    val scanLineY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Stage 1: Photo reading
        scanLineY.animateTo(
            targetValue = 1f,
            animationSpec = tween(1200, easing = LinearEasing)
        )
        completedStageIndex = 1
        delay(600)

        // Stage 2: Category
        completedStageIndex = 2
        delay(600)

        // Stage 3: Color
        completedStageIndex = 3
        delay(600)

        // Stage 4: Brand
        completedStageIndex = 4
        delay(600)

        // Phase transitions to searching
        phase = 1
        completedStageIndex = 5
        delay(800)

        // Phase transitions to results ready
        completedStageIndex = 6
        phase = 2
    }

    val stages = listOf(
        Pair("Reading your photo", null),
        Pair("Category", "Earbuds case"),
        Pair("Color", "White"),
        Pair("Brand", "Apple"),
        Pair("Understanding your description", null),
        Pair("Searching found reports", "128 checked")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Indigo800, Navy900),
                    radius = 1100f
                )
            )
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onBackgroundClick) {
                    Text(
                        text = Strings.get("continue_in_background", lang),
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Photo inside Match Seal Ring
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_airpods_lost),
                    contentDescription = null,
                    modifier = Modifier
                        .size(170.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )

                // Optional scan-line sweep
                if (scanLineY.value < 0.99f) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Signal500)
                    )
                }

                // Match Seal ring wrapping the photo
                MatchSeal(
                    score = if (phase == 2) 93 else 0,
                    size = 200.dp,
                    isDarkSurface = true,
                    animate = phase == 2
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Dynamic Headline
            val headline = when (phase) {
                0 -> Strings.get("ai_analyzing_headline", lang)
                1 -> Strings.get("ai_searching_headline", lang)
                else -> Strings.get("ai_found_headline", lang)
            }

            Text(
                text = headline,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stage Checklist
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Navy700.copy(alpha = 0.5f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                stages.forEachIndexed { idx, stage ->
                    val isDone = idx < completedStageIndex
                    val isActive = idx == completedStageIndex

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isDone) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Signal500,
                                    modifier = Modifier.size(18.dp)
                                )
                            } else if (isActive) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    color = Signal500,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(CircleShape)
                                        .background(Navy700)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = stage.first,
                                fontSize = 13.sp,
                                color = if (isDone || isActive) Color.White else Color.White.copy(alpha = 0.4f),
                                fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }

                        if (stage.second != null && isDone) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Navy700)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = stage.second!!,
                                    fontSize = 11.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // CTA Button if ready
            if (phase == 2) {
                AccentDarkButton(
                    text = Strings.get("see_matches", lang),
                    onClick = onSeeMatches,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 36.dp),
                    height = 56.dp
                )
            } else {
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }
}
