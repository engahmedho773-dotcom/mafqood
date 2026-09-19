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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MatchCandidate
import com.example.ui.components.InverseDarkButton
import com.example.ui.components.PinCheckMark
import com.example.ui.components.PrimaryButton
import com.example.ui.components.SecondaryButton
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Error50
import com.example.ui.theme.Error600
import com.example.ui.theme.Navy700
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal500
import com.example.ui.theme.Success50
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

// S19: Claim Intro Bottom Sheet
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimIntroSheet(
    onDismiss: () -> Unit,
    onContinue: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Teal50),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Security, contentDescription = null, tint = Teal600)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = Strings.get("lets_confirm_yours", lang),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = Strings.get("claim_intro_sub", lang),
                fontSize = 14.sp,
                color = TextMuted,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 3 Steps
            ClaimStepRow(1, Strings.get("step1_answer_q", lang))
            Spacer(modifier = Modifier.height(10.dp))
            ClaimStepRow(2, Strings.get("step2_notify_finder", lang))
            Spacer(modifier = Modifier.height(10.dp))
            ClaimStepRow(3, Strings.get("step3_collect_safely", lang))

            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Lock, contentDescription = null, tint = TextMuted, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = Strings.get("no_id_needed", lang),
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = Strings.get("continue", lang),
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ClaimStepRow(number: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Teal50),
            contentAlignment = Alignment.Center
        ) {
            Text("$number", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Teal600)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text, fontSize = 14.sp, color = Navy900, fontWeight = FontWeight.Medium)
    }
}

// S20: Verification Question
@Composable
fun VerificationQuestionScreen(
    match: MatchCandidate,
    onVerified: () -> Unit,
    onBack: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    var selectedOptionIndex by remember { mutableIntStateOf(-1) }
    var triesLeft by remember { mutableIntStateOf(2) }
    var showErrorNote by remember { mutableStateOf(false) }

    val options = if (isAr) match.optionsAr else match.optionsEn
    val question = if (isAr) match.verificationQuestionAr else match.verificationQuestionEn

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
                text = Strings.get("verify_ownership_title", lang),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Secure Badge
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Teal50)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Security, contentDescription = null, tint = Teal600, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = Strings.get("private_to_finder", lang),
                    fontSize = 12.sp,
                    color = Teal600,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = question,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Only the owner is likely to know this.",
                fontSize = 14.sp,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Options List
            options.forEachIndexed { index, optionText ->
                val isSelected = selectedOptionIndex == index
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) Teal50 else Color.White)
                        .border(1.dp, if (isSelected) Teal600 else BorderStrong, RoundedCornerShape(12.dp))
                        .clickable {
                            selectedOptionIndex = index
                            showErrorNote = false
                        }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = optionText,
                        fontSize = 15.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = Navy900
                    )
                    RadioButton(
                        selected = isSelected,
                        onClick = {
                            selectedOptionIndex = index
                            showErrorNote = false
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Teal600)
                    )
                }
            }

            // Error notice if wrong answer
            if (showErrorNote) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Error50)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "That doesn't match what we have. $triesLeft try left.",
                        fontSize = 13.sp,
                        color = Error600,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "$triesLeft tries left",
                fontSize = 12.sp,
                color = TextMuted
            )
        }

        // Sticky Footer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            PrimaryButton(
                text = Strings.get("confirm", lang),
                onClick = {
                    if (selectedOptionIndex == match.correctOptionIndex) {
                        onVerified()
                    } else {
                        triesLeft--
                        showErrorNote = true
                        if (triesLeft <= 0) {
                            // Even after 2 tries in demo, we verify or let them review
                            onVerified()
                        }
                    }
                },
                enabled = selectedOptionIndex != -1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// S21: Ownership Verified
@Composable
fun OwnershipVerifiedScreen(
    onArrangePickup: () -> Unit
) {
    val lang = LocalAppLanguage.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Pin-check mark in green circle
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Success50),
            contentAlignment = Alignment.Center
        ) {
            PinCheckMark(
                size = 80.dp,
                pinColor = Success600,
                checkColor = Color.White,
                ringColor = Success600,
                animate = true
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = Strings.get("ownership_verified_title", lang),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Navy900,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = Strings.get("ownership_verified_sub", lang),
            fontSize = 15.sp,
            color = TextMuted,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        // 3-item checklist
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                VerifiedCheckRow(text = "Ownership verified", isDone = true)
                Spacer(modifier = Modifier.height(10.dp))
                VerifiedCheckRow(text = "Match confirmed", isDone = true)
                Spacer(modifier = Modifier.height(10.dp))
                VerifiedCheckRow(text = "Recovery to be arranged", isDone = false)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        PrimaryButton(
            text = Strings.get("arrange_pickup", lang),
            onClick = onArrangePickup,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun VerifiedCheckRow(text: String, isDone: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(if (isDone) Success50 else CanvasBg)
                .border(1.dp, if (isDone) Success600 else BorderStrong, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Success600, modifier = Modifier.size(12.dp))
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isDone) Navy900 else TextMuted,
            fontWeight = if (isDone) FontWeight.Medium else FontWeight.Normal
        )
    }
}

// S22: Recovery Arrangement
@Composable
fun RecoveryArrangementScreen(
    onConfirmCollected: () -> Unit,
    onBack: () -> Unit
) {
    val lang = LocalAppLanguage.current
    var showConfirmDialog by remember { mutableStateOf(false) }

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
                text = Strings.get("recovery_title", lang),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Stepper card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    RecoveryStepItem("✓  Ownership verified", true)
                    RecoveryStepItem("✓  Match confirmed", true)
                    RecoveryStepItem("●  Recovery arranged (Ready for pickup)", true, isCurrent = true)
                    RecoveryStepItem("○  Recovered", false)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Pickup Desk Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = Strings.get("pickup_point_overline", lang),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Teal600,
                        letterSpacing = 0.6.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = Strings.get("campus_security_desk", lang),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Navy900
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = Strings.get("admin_bldg_floor", lang),
                        fontSize = 14.sp,
                        color = TextMuted
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Schedule, contentDescription = null, tint = TextMuted, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = Strings.get("desk_hours", lang),
                            fontSize = 13.sp,
                            color = TextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Case Reference Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Teal50)
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = Strings.get("case_ref", lang), fontSize = 11.sp, color = TextMuted)
                            Text(text = "MFQ-2841", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Teal600)
                        }
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy code", tint = Teal600, modifier = Modifier.size(18.dp))
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = Strings.get("show_at_desk", lang),
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Meet finder option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, BorderStrong, RoundedCornerShape(12.dp))
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Place, contentDescription = null, tint = Teal600, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = Strings.get("meet_finder_instead", lang), fontSize = 14.sp, color = Navy900)
                }
            }
        }

        // Sticky Footer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            PrimaryButton(
                text = Strings.get("collected_item_btn", lang),
                onClick = { showConfirmDialog = true },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Confirm Recovery", fontWeight = FontWeight.SemiBold) },
            text = { Text("Have you safely collected your item from the campus security desk?") },
            confirmButton = {
                TextButton(onClick = {
                    showConfirmDialog = false
                    onConfirmCollected()
                }) {
                    Text("Yes, I have it", color = Teal600, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Not yet", color = TextMuted)
                }
            },
            containerColor = Color.White
        )
    }
}

@Composable
private fun RecoveryStepItem(text: String, isDone: Boolean, isCurrent: Boolean = false) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = if (isCurrent) FontWeight.SemiBold else FontWeight.Normal,
        color = if (isCurrent) Teal600 else if (isDone) Success600 else TextMuted,
        modifier = Modifier.padding(vertical = 3.dp)
    )
}

// S23: Recovered (Closing dark navy bookend screen)
@Composable
fun RecoveredScreen(
    onBackHome: () -> Unit,
    onViewSummary: () -> Unit
) {
    val lang = LocalAppLanguage.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy900)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PinCheckMark(
            size = 96.dp,
            pinColor = Color.White,
            checkColor = Success600,
            ringColor = Success600,
            animate = true
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = Strings.get("recovered_title", lang),
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = Strings.get("recovered_sub", lang),
            fontSize = 15.sp,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Timeline Summary Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Navy700)
                .padding(18.dp)
        ) {
            Text(
                text = "Reported 11:05 AM  →  Match found 11:07 AM  →  Recovered 12:47 PM",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = Strings.get("total_time", lang),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Signal500
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Thanks for using MAFQOOD. The finder has been notified.",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(48.dp))

        InverseDarkButton(
            text = Strings.get("back_to_home", lang),
            onClick = onBackHome,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onViewSummary) {
            Text(
                text = Strings.get("view_case_summary", lang),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }
    }
}
