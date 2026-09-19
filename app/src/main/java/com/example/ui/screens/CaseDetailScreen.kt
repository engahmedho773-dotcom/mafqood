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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CaseStatus
import com.example.data.ReportItem
import com.example.data.ReportType
import com.example.ui.components.HorizontalJourneyLine
import com.example.ui.components.PrimaryButton
import com.example.ui.components.SecondaryButton
import com.example.ui.components.StatusBadge
import com.example.ui.components.VerticalJourneyTimeline
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

// S15: Case Detail Screen
@Composable
fun CaseDetailScreen(
    caseItem: ReportItem,
    onViewMatch: () -> Unit,
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Navy900)
                }
                Text(
                    text = "Case #${caseItem.referenceCode}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
            }
            StatusBadge(status = caseItem.status)
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            // Photo & Title Card
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = caseItem.imageDrawableRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(76.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = if (isAr) caseItem.titleAr else caseItem.title,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 17.sp,
                                    color = Navy900
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "${if (isAr) caseItem.locationAr else caseItem.location} · ${caseItem.timeDescription}",
                                    fontSize = 13.sp,
                                    color = TextMuted
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (caseItem.type == ReportType.Lost) "Lost report" else "Found report",
                                    fontSize = 12.sp,
                                    color = Teal600,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        if (caseItem.description.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = caseItem.description,
                                fontSize = 13.sp,
                                color = Navy900,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 7-Node Journey Timeline (Spec 1.1)
            item {
                Text(
                    text = Strings.get("journey_timeline_title", lang),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    VerticalJourneyTimeline(
                        currentStepIndex = caseItem.currentJourneyStep,
                        timestamps = listOf(
                            "11:05 AM",
                            "11:06 AM",
                            "11:07 AM",
                            "11:07 AM",
                            "In progress",
                            "Pending",
                            "Pending"
                        ),
                        details = listOf(
                            "Case reported at University of Bisha",
                            "AI analyzed photo and category features",
                            "Found 1 candidate match (93% score)",
                            "Confidence: Likely match",
                            "Awaiting ownership confirmation",
                            "Security desk pickup",
                            "Recovery completed"
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // If match found, show Action button
            if (caseItem.status == CaseStatus.MatchFound) {
                item {
                    PrimaryButton(
                        text = Strings.get("view_match", lang),
                        onClick = onViewMatch,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // Case Reference & Support info
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Teal50)
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("University Reference", fontSize = 11.sp, color = TextMuted)
                        Text(caseItem.referenceCode, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Teal600)
                    }
                    Icon(Icons.Default.ContentCopy, contentDescription = null, tint = Teal600, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

// All Cases Browser Screen
@Composable
fun AllCasesScreen(
    cases: List<ReportItem>,
    onCaseClick: (ReportItem) -> Unit,
    onBack: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    val tabTitles = listOf("All", "Lost", "Found", "Recovered")

    val filteredCases = cases.filter { case ->
        val matchesTab = when (selectedTab) {
            1 -> case.type == ReportType.Lost
            2 -> case.type == ReportType.Found
            3 -> case.status == CaseStatus.Recovered
            else -> true
        }
        val matchesSearch = case.title.contains(searchQuery, ignoreCase = true) ||
                case.titleAr.contains(searchQuery) ||
                case.location.contains(searchQuery, ignoreCase = true)
        matchesTab && (searchQuery.isEmpty() || matchesSearch)
    }

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
                text = Strings.get("all_cases_title", lang),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
        }

        // Search Bar
        Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search your cases...", fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextMuted) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Teal600,
                    unfocusedBorderColor = BorderStrong,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tabs Row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Teal600,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Teal600
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.SemiBold else FontWeight.Normal,
                            fontSize = 14.sp,
                            color = if (selectedTab == index) Teal600 else TextMuted
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            items(filteredCases.size) { idx ->
                val caseItem = filteredCases[idx]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White)
                        .border(1.dp, BorderColor, RoundedCornerShape(14.dp))
                        .clickable { onCaseClick(caseItem) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = caseItem.imageDrawableRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isAr) caseItem.titleAr else caseItem.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Navy900
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${if (isAr) caseItem.locationAr else caseItem.location} · ${caseItem.timeDescription}",
                            fontSize = 12.sp,
                            color = TextMuted,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        StatusBadge(status = caseItem.status)
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BorderStrong)
                }
            }

            item { Spacer(modifier = Modifier.height(36.dp)) }
        }
    }
}
