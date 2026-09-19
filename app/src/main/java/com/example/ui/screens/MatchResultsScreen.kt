package com.example.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MatchCandidate
import com.example.data.MatchTier
import com.example.ui.components.MatchSeal
import com.example.ui.components.SecondaryButton
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal800
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

@Composable
fun MatchResultsScreen(
    bestMatch: MatchCandidate,
    otherMatches: List<MatchCandidate>,
    onSelectMatch: (MatchCandidate) -> Unit,
    onBack: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

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
                text = Strings.get("matches_title", lang),
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
            // Your Report Strip
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_airpods_lost),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isAr) "سماعتك المفقودة · كلية العلوم" else "Your lost AirPods case · Science College",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Navy900,
                            maxLines = 1
                        )
                        Text(
                            text = "11:00 AM",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // BEST MATCH Section
            item {
                Text(
                    text = Strings.get("best_match", lang),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Signal800,
                    letterSpacing = 0.6.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Hero Match Card (Spec 3.8 & 4 S17)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onSelectMatch(bestMatch) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Column {
                        // 4:3 Photo with Caption Chip
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Image(
                                painter = painterResource(id = bestMatch.foundReport.imageDrawableRes),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Navy900.copy(alpha = 0.75f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (isAr) "تقرير معثورات" else "Found report",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        // Info & Match Seal
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (isAr) bestMatch.foundReport.titleAr else bestMatch.foundReport.title,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Navy900
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(
                                        text = if (isAr) "عُثر عليه قرب كلية العلوم" else "Found near Science College",
                                        fontSize = 13.sp,
                                        color = Navy900
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = bestMatch.foundReport.timeDescription,
                                        fontSize = 12.sp,
                                        color = TextMuted
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                MatchSeal(
                                    score = bestMatch.overallScore,
                                    tier = bestMatch.tier,
                                    size = 64.dp,
                                    animate = true,
                                    onClick = { onSelectMatch(bestMatch) }
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Why chips
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                val chips = if (isAr) bestMatch.whyChipsAr else bestMatch.whyChipsEn
                                chips.forEach { chip ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Teal50)
                                            .padding(horizontal = 10.dp, vertical = 5.dp)
                                    ) {
                                        Text(chip, fontSize = 11.sp, color = Teal600, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            SecondaryButton(
                                text = Strings.get("view_match", lang),
                                onClick = { onSelectMatch(bestMatch) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))
            }

            // Other possibilities section
            item {
                Text(
                    text = Strings.get("other_possibilities", lang) + " (${otherMatches.size})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(otherMatches.size) { idx ->
                val match = otherMatches[idx]
                CompactMatchCard(
                    match = match,
                    onClick = { onSelectMatch(match) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item { Spacer(modifier = Modifier.height(36.dp)) }
        }
    }
}

@Composable
private fun CompactMatchCard(
    match: MatchCandidate,
    onClick: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = match.foundReport.imageDrawableRes),
            contentDescription = null,
            modifier = Modifier
                .size(68.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (isAr) match.foundReport.titleAr else match.foundReport.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${if (isAr) match.foundReport.locationAr else match.foundReport.location} · ${match.foundReport.timeDescription}",
                fontSize = 12.sp,
                color = TextMuted,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        MatchSeal(
            score = match.overallScore,
            tier = match.tier,
            size = 48.dp,
            animate = false
        )
    }
}
