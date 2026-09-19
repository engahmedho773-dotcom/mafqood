package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ui.components.MatchSeal
import com.example.ui.components.PinCheckMark
import com.example.ui.components.PrimaryButton
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Success50
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val lang = LocalAppLanguage.current
    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
            .padding(horizontal = 24.dp)
    ) {
        // Top bar with Skip button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onFinish) {
                Text(
                    text = Strings.get("skip", lang),
                    color = Teal600,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Center visual card
        AnimatedContent(
            targetState = currentPage,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "onboarding_page",
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when (page) {
                    0 -> OnboardingVisualPage1()
                    1 -> OnboardingVisualPage2()
                    2 -> OnboardingVisualPage3()
                }

                Spacer(modifier = Modifier.height(36.dp))

                val titleKey = when (page) {
                    0 -> "onboarding_title_1"
                    1 -> "onboarding_title_2"
                    else -> "onboarding_title_3"
                }
                val bodyKey = when (page) {
                    0 -> "onboarding_body_1"
                    1 -> "onboarding_body_2"
                    else -> "onboarding_body_3"
                }

                Text(
                    text = Strings.get(titleKey, lang),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = Strings.get(bodyKey, lang),
                    fontSize = 15.sp,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }

        // Page Indicator Dots
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until 3) {
                val isActive = i == currentPage
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(8.dp)
                        .width(if (isActive) 24.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (isActive) Teal600 else BorderStrong)
                )
            }
        }

        // Footer CTA
        PrimaryButton(
            text = if (currentPage < 2) Strings.get("next", lang) else Strings.get("get_started", lang),
            onClick = {
                if (currentPage < 2) {
                    currentPage++
                } else {
                    onFinish()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp)
        )
    }
}

@Composable
private fun OnboardingVisualPage1() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_airpods_lost),
            contentDescription = "Lost item photo",
            modifier = Modifier
                .size(190.dp)
                .clip(RoundedCornerShape(14.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailChip(icon = Icons.Default.CameraAlt, text = "Photo")
            DetailChip(icon = Icons.Default.Place, text = "Where")
            DetailChip(icon = Icons.Default.AccessTime, text = "When")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "≈ 45 seconds to submit",
            fontSize = 12.sp,
            color = TextMuted,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun DetailChip(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Teal50)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Teal600, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = 12.sp, color = Teal600, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun OnboardingVisualPage2() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        contentAlignment = Alignment.Center
    ) {
        // Lost card
        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopStart)
                .padding(start = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_airpods_lost),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Found card
        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_airpods_found),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Overlapping Match Seal
        MatchSeal(
            score = 93,
            size = 80.dp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun OnboardingVisualPage3() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Success50),
            contentAlignment = Alignment.Center
        ) {
            PinCheckMark(
                size = 56.dp,
                pinColor = Success600,
                checkColor = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Campus Security Desk",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Navy900
        )
        Text(
            text = "Admin Building · Ground floor",
            fontSize = 13.sp,
            color = TextMuted
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Teal50)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Security, contentDescription = null, tint = Teal600, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "One quick question. No documents.",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Teal600
            )
        }
    }
}
