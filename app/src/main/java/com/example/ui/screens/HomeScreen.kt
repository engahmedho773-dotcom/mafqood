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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.data.CaseStatus
import com.example.data.ReportItem
import com.example.ui.components.AccentDarkButton
import com.example.ui.components.HorizontalJourneyLine
import com.example.ui.components.MatchSeal
import com.example.ui.components.StatusBadge
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal50
import com.example.ui.theme.Signal500
import com.example.ui.theme.Signal600
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

@Composable
fun HomeScreen(
    activeReports: List<ReportItem>,
    onLostClick: () -> Unit,
    onFoundClick: () -> Unit,
    onReviewMatchClick: () -> Unit,
    onCaseClick: (ReportItem) -> Unit,
    onSeeAllClick: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    // Check if there is an active match needing attention
    val matchAttentionCase = activeReports.firstOrNull { it.status == CaseStatus.MatchFound }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
            .padding(horizontal = 24.dp)
    ) {
        // Safe top spacing
        item { Spacer(modifier = Modifier.height(48.dp)) }

        // Header Greeting Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Teal50)
                        .border(1.5.dp, Teal600, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "RA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Teal600
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = Strings.get("greeting_morning", lang),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Navy900
                    )
                    Text(
                        text = Strings.get("univ_bisha", lang),
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        // Two Hero Action Tiles side-by-side (165 x 148 dp)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // I Lost Something (Solid Teal)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(148.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Teal600)
                        .clickable { onLostClick() }
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Column {
                            Text(
                                text = Strings.get("tile_lost_title", lang),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = Strings.get("tile_lost_sub", lang),
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.8f),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                // I Found Something (White with Signal Orange Circle)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(148.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                        .clickable { onFoundClick() }
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Signal50),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.FrontHand,
                                contentDescription = null,
                                tint = Signal600,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Column {
                            Text(
                                text = Strings.get("tile_found_title", lang),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Navy900
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = Strings.get("tile_found_sub", lang),
                                fontSize = 12.sp,
                                color = TextMuted,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Needs Attention Card (if match found)
        if (matchAttentionCase != null) {
            item {
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = Strings.get("needs_attention", lang),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Navy900)
                        .padding(16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MatchSeal(
                                score = 93,
                                size = 60.dp,
                                isDarkSurface = true,
                                animate = false
                            )

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = Strings.get("possible_match", lang),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Signal500,
                                    letterSpacing = 0.6.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (isAr) matchAttentionCase.titleAr else matchAttentionCase.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (isAr) "عُثر عليه قرب كلية العلوم · 11:20 ص" else "Found near Science College · 11:20 AM",
                                    fontSize = 12.sp,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        AccentDarkButton(
                            text = Strings.get("review_match", lang),
                            onClick = onReviewMatchClick,
                            modifier = Modifier.fillMaxWidth(),
                            height = 46.dp
                        )
                    }
                }
            }
        }

        // Active Cases Section
        item {
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.get("active_cases", lang),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                TextButton(onClick = onSeeAllClick) {
                    Text(
                        text = Strings.get("see_all", lang),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Teal600
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (activeReports.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = Strings.get("nothing_lost_title", lang),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Navy900
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = Strings.get("nothing_lost_body", lang),
                        fontSize = 14.sp,
                        color = TextMuted,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
        } else {
            items(activeReports.size) { index ->
                val caseItem = activeReports[index]
                HomeCaseItemRow(
                    caseItem = caseItem,
                    onClick = { onCaseClick(caseItem) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        // Bottom navigation padding
        item { Spacer(modifier = Modifier.height(96.dp)) }
    }
}

@Composable
private fun HomeCaseItemRow(
    caseItem: ReportItem,
    onClick: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = caseItem.imageDrawableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isAr) caseItem.titleAr else caseItem.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Navy900
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${if (isAr) caseItem.locationAr else caseItem.location} · ${caseItem.timeDescription}",
                        fontSize = 12.sp,
                        color = TextMuted,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    StatusBadge(status = caseItem.status)
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = BorderStrong,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalJourneyLine(
                currentStepIndex = caseItem.currentJourneyStep,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
