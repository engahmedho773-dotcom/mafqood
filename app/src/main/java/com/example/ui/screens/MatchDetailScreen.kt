package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MatchCandidate
import com.example.data.ReportItem
import com.example.ui.components.MatchSeal
import com.example.ui.components.PrimaryButton
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal500
import com.example.ui.theme.Signal600
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

@Composable
fun MatchDetailScreen(
    match: MatchCandidate,
    lostReport: ReportItem,
    onStartClaim: () -> Unit,
    onNotMine: () -> Unit,
    onBack: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC
    var showBreakdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(top = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Navy900)
            }
            Text(
                text = Strings.get("match_details_title", lang),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            // 1. Comparison Pair (1:1 photos with link badge)
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Lost photo
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = Strings.get("you_lost", lang),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMuted,
                                letterSpacing = 0.6.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Image(
                                painter = painterResource(id = R.drawable.img_airpods_lost),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isAr) lostReport.titleAr else lostReport.title,
                                fontSize = 12.sp,
                                color = Navy900,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1
                            )
                        }

                        // Found photo
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = Strings.get("found", lang),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Signal600,
                                letterSpacing = 0.6.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Image(
                                painter = painterResource(id = match.foundReport.imageDrawableRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isAr) match.foundReport.titleAr else match.foundReport.title,
                                fontSize = 12.sp,
                                color = Navy900,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1
                            )
                        }
                    }

                    // Link Icon Badge overlapping
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(1.5.dp, BorderColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Link,
                            contentDescription = null,
                            tint = Teal600,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // 2. Score Panel (Navy900 hero surface)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Navy900)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MatchSeal(
                        score = match.overallScore,
                        tier = match.tier,
                        size = 156.dp,
                        isDarkSurface = true,
                        animate = true,
                        onClick = { showBreakdown = !showBreakdown }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (isAr) match.summaryAr else match.summaryEn,
                        fontSize = 15.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (showBreakdown) "Hide score breakdown" else "Tap seal for score breakdown",
                        fontSize = 11.sp,
                        color = Signal500,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { showBreakdown = !showBreakdown }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Expandable: "How the score is made"
            item {
                AnimatedVisibility(visible = showBreakdown) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = Strings.get("how_score_made", lang),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = Navy900
                            )
                            Spacer(modifier = Modifier.height(14.dp))

                            ScoreSignalRow("Photo looks alike", match.visualScore, 35)
                            ScoreSignalRow("Description reads alike", match.descriptionScore, 25)
                            ScoreSignalRow("Same kind of item", match.attributesScore, 15)
                            ScoreSignalRow("Close by location", match.locationScore, 15)
                            ScoreSignalRow("Right time window", match.timeScore, 10)

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Scores are estimates that help you decide. You'll still verify ownership.",
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }
                    }
                }
            }

            // 3. "Why we think this matches"
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = Strings.get("why_think_matches", lang),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Navy900
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        MatchReasonRow("Same category and brand (Apple AirPods)")
                        MatchReasonRow("Nearby location (Science College campus)")
                        MatchReasonRow("Found 20 minutes after reported loss time")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 4. Side-by-side details compare table
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = Strings.get("compare_table_title", lang),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Navy900
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        CompareRow("Category", "Earbuds case", "Earbuds case", true)
                        CompareRow("Color", "White", "White", true)
                        CompareRow("Brand", "Apple", "Apple", true)
                        CompareRow("Where", "Science College", "Science College", true)
                        CompareRow("When", "11:00 AM", "11:20 AM", true)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Sticky Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                text = Strings.get("start_ownership_check", lang),
                onClick = onStartClaim,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextButton(onClick = onNotMine) {
                Text(
                    text = Strings.get("this_isnt_mine", lang),
                    color = TextMuted,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun ScoreSignalRow(label: String, score: Int, weight: Int) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, fontSize = 13.sp, color = Navy900)
            Text("$score% ($weight% weight)", fontSize = 12.sp, color = TextMuted, fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = Modifier.height(3.dp))
        LinearProgressIndicator(
            progress = { score / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = Teal600,
            trackColor = BorderColor
        )
    }
}

@Composable
private fun MatchReasonRow(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Check,
            contentDescription = null,
            tint = Success600,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 13.sp, color = Navy900)
    }
}

@Composable
private fun CompareRow(label: String, val1: String, val2: String, matches: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = TextMuted, modifier = Modifier.width(72.dp))
        Text(text = val1, fontSize = 13.sp, color = Navy900, modifier = Modifier.weight(1f))
        Text(text = val2, fontSize = 13.sp, color = Navy900, modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.Check,
            contentDescription = null,
            tint = if (matches) Success600 else TextMuted,
            modifier = Modifier.size(16.dp)
        )
    }
}
