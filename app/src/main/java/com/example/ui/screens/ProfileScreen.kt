package com.example.ui.screens

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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.SecondaryButton
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Error600
import com.example.ui.theme.Navy900
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

@Composable
fun ProfileScreen(
    onToggleLanguage: () -> Unit,
    onSignOut: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
            .padding(horizontal = 24.dp)
    ) {
        item { Spacer(modifier = Modifier.height(48.dp)) }

        // Header Title
        item {
            Text(
                text = Strings.get("profile_title", lang),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Student Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Teal50)
                            .border(2.dp, Teal600, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "RA",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Teal600
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = if (isAr) "ريم القحطاني" else "Reem Al-Qahtani",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Navy900
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "reem.alqahtani@ub.edu.sa",
                            fontSize = 13.sp,
                            color = TextMuted
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isAr) "جامعة بيشة · كلية الحاسب وتقنية المعلومات" else "University of Bisha · College of Computing",
                            fontSize = 12.sp,
                            color = Teal600,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Language Switcher Setting
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Language, contentDescription = null, tint = Teal600)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = Strings.get("language", lang),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Navy900
                            )
                            Text(
                                text = if (isAr) "العربية (Arabic)" else "English (الإنجليزية)",
                                fontSize = 12.sp,
                                color = TextMuted
                            )
                        }
                    }

                    Switch(
                        checked = isAr,
                        onCheckedChange = { onToggleLanguage() },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Teal600
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Privacy Guarantee Card (Spec 1.1)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Teal600, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = Strings.get("privacy_title", lang),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Navy900
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = Strings.get("privacy_guarantee", lang),
                        fontSize = 13.sp,
                        color = TextMuted,
                        lineHeight = 19.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Campus Security Desk Support
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Security, contentDescription = null, tint = Teal600, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Campus Security Desk",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Navy900
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Main Administration Building · Ground Floor\nSun - Thu: 8:00 AM - 4:00 PM\nDirect Line: +966 17 623 8888 (Ext 4400)",
                        fontSize = 13.sp,
                        color = TextMuted,
                        lineHeight = 18.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Sign Out Button
        item {
            SecondaryButton(
                text = "Sign out",
                onClick = onSignOut,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        // App Version
        item {
            Text(
                text = "MAFQOOD v1.0.0 · University of Bisha\nAI Studio Edition",
                fontSize = 12.sp,
                color = TextMuted,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(96.dp))
        }
    }
}
