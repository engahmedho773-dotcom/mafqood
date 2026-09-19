package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.PinCheckMark
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.Indigo800
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal500
import com.example.ui.theme.Teal600
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    val lang = LocalAppLanguage.current

    LaunchedEffect(Unit) {
        delay(2200)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Indigo800, Navy900),
                    radius = 1200f
                )
            )
            .clickable { onTimeout() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            PinCheckMark(
                size = 76.dp,
                pinColor = Color.White,
                checkColor = Teal600,
                animate = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "MAFQOOD",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "مَفْقُود",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Signal500
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = Strings.get("app_tagline", lang),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 280.dp),
                lineHeight = 26.sp
            )
        }

        Text(
            text = Strings.get("campus_subtitle", lang),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.6f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        )
    }
}
