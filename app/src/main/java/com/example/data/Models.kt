package com.example.data

import com.example.R

enum class CaseStatus(val stepIndex: Int) {
    Searching(1),
    MatchFound(2),
    VerificationRequired(4),
    ClaimUnderReview(4),
    RecoveryPending(5),
    Recovered(6),
    Closed(6)
}

enum class MatchTier {
    Likely,
    Possible,
    LowConfidence
}

enum class ReportType {
    Lost,
    Found
}

data class CampusBuilding(
    val id: String,
    val nameEn: String,
    val nameAr: String,
    val zone: String,
    val floorOptions: List<String> = listOf("Entrance", "Ground floor", "Upper floors", "Hall", "Café", "Outside")
)

data class ReportItem(
    val id: String,
    val type: ReportType,
    val title: String,
    val titleAr: String = "",
    val category: String,
    val categoryAr: String = "",
    val color: String,
    val colorAr: String = "",
    val brand: String,
    val features: List<String> = emptyList(),
    val location: String,
    val locationAr: String = "",
    val zoneDetail: String = "",
    val timeDescription: String,
    val description: String = "",
    val imageDrawableRes: Int,
    val status: CaseStatus,
    val currentJourneyStep: Int = 0, // 0..6 (REPORT, ANALYZE, MATCH, SCORE, CLAIM, VERIFY, RECOVER)
    val createdAt: String = "Today",
    val referenceCode: String = "MFQ-2841",
    val whereIsItNow: String? = null,
    val secretDetail: String? = null
)

data class MatchCandidate(
    val id: String,
    val foundReport: ReportItem,
    val overallScore: Int,
    val tier: MatchTier,
    val visualScore: Int,
    val descriptionScore: Int,
    val attributesScore: Int,
    val locationScore: Int,
    val timeScore: Int,
    val summaryEn: String,
    val summaryAr: String,
    val whyChipsEn: List<String>,
    val whyChipsAr: List<String>,
    val verificationQuestionEn: String,
    val verificationQuestionAr: String,
    val optionsEn: List<String>,
    val optionsAr: List<String>,
    val correctOptionIndex: Int
)

data class NotificationItem(
    val id: String,
    val type: String, // "match", "verification", "recovery", "claim"
    val titleEn: String,
    val titleAr: String,
    val bodyEn: String,
    val bodyAr: String,
    val time: String,
    val isUnread: Boolean,
    val relatedCaseId: String
)
