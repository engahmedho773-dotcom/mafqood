package com.example.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CaseStatus
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.theme.Signal50
import com.example.ui.theme.Signal800
import com.example.ui.theme.Success50
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.Teal700
import com.example.ui.theme.TextMuted
import com.example.ui.theme.Warning50
import com.example.ui.theme.Warning700

/**
 * Pill Status Badge (Spec 3.8)
 * 24dp height, 8dp horizontal padding, 12dp icon + caption label.
 */
@Composable
fun StatusBadge(
    status: CaseStatus,
    modifier: Modifier = Modifier
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    val (bgColor, textColor, label) = when (status) {
        CaseStatus.Searching -> Triple(
            Teal50,
            Teal600,
            if (isAr) "جاري البحث" else "Searching"
        )
        CaseStatus.MatchFound -> Triple(
            Signal50,
            Signal800,
            if (isAr) "تم العثور على تطابق" else "Match Found"
        )
        CaseStatus.VerificationRequired -> Triple(
            Color(0xFFE9EAF6),
            Color(0xFF21295C),
            if (isAr) "التحقق مطلوب" else "Verification Required"
        )
        CaseStatus.ClaimUnderReview -> Triple(
            Warning50,
            Warning700,
            if (isAr) "المطالبة قيد المراجعة" else "Claim Under Review"
        )
        CaseStatus.RecoveryPending -> Triple(
            Color(0xFFE3EEF8),
            Teal700,
            if (isAr) "بانتظار الاستلام" else "Recovery Pending"
        )
        CaseStatus.Recovered -> Triple(
            Success50,
            Success600,
            if (isAr) "تم الاسترداد" else "Recovered"
        )
        CaseStatus.Closed -> Triple(
            Color(0xFFEEF1F6),
            TextMuted,
            if (isAr) "مغلق" else "Closed"
        )
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (status == CaseStatus.Searching) {
            // Pulsing 8px dot
            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.4f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(800),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "alpha"
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .alpha(alpha)
                    .clip(CircleShape)
                    .background(textColor)
            )
        } else {
            val icon = when (status) {
                CaseStatus.MatchFound -> Icons.Default.Link
                CaseStatus.VerificationRequired -> Icons.Default.Shield
                CaseStatus.ClaimUnderReview -> Icons.Default.HourglassEmpty
                CaseStatus.RecoveryPending -> Icons.Default.Schedule
                CaseStatus.Recovered -> Icons.Default.CheckCircle
                CaseStatus.Closed -> Icons.Default.Archive
                else -> Icons.Default.CheckCircle
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(12.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = label,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
