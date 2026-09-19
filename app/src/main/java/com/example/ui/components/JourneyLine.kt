package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.Navy900
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

/**
 * 7-Node Journey Line (Spec 1.1 & 2.4)
 * REPORT · ANALYZE · MATCH · SCORE · CLAIM · VERIFY · RECOVER
 */
object JourneySteps {
    val stepsEn = listOf("REPORT", "ANALYZE", "MATCH", "SCORE", "CLAIM", "VERIFY", "RECOVER")
    val stepsAr = listOf("إبلاغ", "تحليل", "مطابقة", "تقييم", "مطالبة", "تحقق", "استرداد")
}

@Composable
fun HorizontalJourneyLine(
    currentStepIndex: Int, // 0..6
    modifier: Modifier = Modifier
) {
    val lang = LocalAppLanguage.current
    val labels = if (lang == AppLanguage.ARABIC) JourneySteps.stepsAr else JourneySteps.stepsEn
    val currentLabel = labels.getOrElse(currentStepIndex) { "" }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 7) {
                val isPassed = i < currentStepIndex
                val isCurrent = i == currentStepIndex

                Box(
                    modifier = Modifier
                        .size(if (isCurrent) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isPassed -> Teal600
                                isCurrent -> Teal600
                                else -> BorderStrong
                            }
                        )
                        .then(
                            if (isCurrent) {
                                Modifier.border(2.dp, Teal50, CircleShape)
                            } else Modifier
                        )
                )

                if (i < 6) {
                    val connectorPassed = i < currentStepIndex
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .padding(horizontal = 2.dp)
                            .background(if (connectorPassed) Teal600 else BorderColor)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = currentLabel,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = TextMuted
        )
    }
}

@Composable
fun VerticalJourneyTimeline(
    currentStepIndex: Int,
    timestamps: List<String>,
    details: List<String>,
    modifier: Modifier = Modifier
) {
    val lang = LocalAppLanguage.current
    val labels = if (lang == AppLanguage.ARABIC) JourneySteps.stepsAr else JourneySteps.stepsEn

    Column(modifier = modifier) {
        for (i in 0 until 7) {
            val isPassed = i < currentStepIndex
            val isCurrent = i == currentStepIndex
            val stepLabel = labels.getOrElse(i) { "" }
            val time = timestamps.getOrElse(i) { "" }
            val detail = details.getOrElse(i) { "" }

            Row(modifier = Modifier.fillMaxWidth()) {
                // Left node column with connecting line
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(28.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(if (isCurrent) 14.dp else 10.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isPassed -> Success600
                                    isCurrent -> Teal600
                                    else -> BorderStrong
                                }
                            )
                            .then(
                                if (isCurrent) {
                                    Modifier.border(2.dp, Teal50, CircleShape)
                                } else Modifier
                            )
                    )

                    if (i < 6) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(38.dp)
                                .background(if (isPassed) Success600 else BorderColor)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Right text column
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = if (i < 6) 16.dp else 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stepLabel,
                            fontWeight = if (isCurrent) FontWeight.SemiBold else FontWeight.Medium,
                            fontSize = 14.sp,
                            color = if (isCurrent) Teal600 else if (isPassed) Navy900 else TextMuted
                        )
                        if (time.isNotEmpty()) {
                            Text(
                                text = time,
                                fontSize = 12.sp,
                                color = TextMuted
                            )
                        }
                    }

                    if (detail.isNotEmpty()) {
                        Text(
                            text = detail,
                            fontSize = 12.sp,
                            color = TextMuted,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
