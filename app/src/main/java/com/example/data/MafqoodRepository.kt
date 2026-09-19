package com.example.data

import com.example.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MafqoodRepository {

    val campusBuildings = listOf(
        CampusBuilding("b1", "Science College", "كلية العلوم", "Zone A"),
        CampusBuilding("b2", "Central Library", "المكتبة المركزية", "Zone B"),
        CampusBuilding("b3", "Engineering College", "كلية الهندسة", "Zone A"),
        CampusBuilding("b4", "Main Cafeteria", "الكافيتريا الرئيسية", "Zone C"),
        CampusBuilding("b5", "Admin Building", "المبنى الإداري الرئيسي", "Zone D"),
        CampusBuilding("b6", "Sports Hall", "الصالة الرياضية", "Zone E"),
        CampusBuilding("b7", "University Mosque", "جامع الجامعة", "Zone B"),
        CampusBuilding("b8", "Faculty Parking P1", "مواقف P1", "Zone F"),
        CampusBuilding("b9", "Student Housing Block 2", "سكن الطلاب مبنى 2", "Zone G")
    )

    private val initialLostCase = ReportItem(
        id = "MFQ-2841",
        type = ReportType.Lost,
        title = "White AirPods case",
        titleAr = "حافظة سماعات AirPods بيضاء",
        category = "Earbuds case",
        categoryAr = "حافظة سماعات",
        color = "White",
        colorAr = "أبيض",
        brand = "Apple",
        features = listOf("Small scratch on lid"),
        location = "Science College",
        locationAr = "كلية العلوم",
        zoneDetail = "Ground floor lobby",
        timeDescription = "About 11:00 AM",
        description = "Left on study desk in ground floor lobby around 11:00 AM. Has small scratch on the front lid.",
        imageDrawableRes = R.drawable.img_airpods_lost,
        status = CaseStatus.MatchFound,
        currentJourneyStep = 3, // SCORE
        createdAt = "Today, 11:05 AM",
        referenceCode = "MFQ-2841"
    )

    private val initialFoundCandidate = ReportItem(
        id = "FND-9012",
        type = ReportType.Found,
        title = "White AirPods case",
        titleAr = "سماعة AirPods بيضاء مع الحافظة",
        category = "Earbuds case",
        categoryAr = "حافظة سماعات",
        color = "White",
        colorAr = "أبيض",
        brand = "Apple",
        features = listOf("Clean white finish", "Found on bench"),
        location = "Science College",
        locationAr = "كلية العلوم",
        zoneDetail = "Courtyard wooden bench",
        timeDescription = "Today · 11:20 AM · 20 min after your loss",
        description = "Found resting on the wooden bench in the Science College outdoor walkway. In good condition.",
        imageDrawableRes = R.drawable.img_airpods_found,
        status = CaseStatus.Searching,
        currentJourneyStep = 2,
        createdAt = "Today, 11:20 AM",
        referenceCode = "FND-9012",
        whereIsItNow = "Handed to Campus Security",
        secretDetail = "Small blue sticker"
    )

    val demoMatchCandidate = MatchCandidate(
        id = "match-1",
        foundReport = initialFoundCandidate,
        overallScore = 93,
        tier = MatchTier.Likely,
        visualScore = 91,
        descriptionScore = 88,
        attributesScore = 93,
        locationScore = 95,
        timeScore = 90,
        summaryEn = "Very likely the same item. Photos, place and time all line up.",
        summaryAr = "تطابق مرجّح جداً لنفس الغرض. الصور والموقع والوقت متطابقة بدقة.",
        whyChipsEn = listOf("Same color", "Same brand", "Found nearby"),
        whyChipsAr = listOf("نفس اللون", "نفس الماركة", "عُثر عليه بالقرب"),
        verificationQuestionEn = "What's on the inside of the lid?",
        verificationQuestionAr = "ما الذي يوجد داخل غطاء الحافظة؟",
        optionsEn = listOf("Small blue sticker", "Initials \"R.A.\"", "Nothing, it's clean", "Not sure"),
        optionsAr = listOf("ملصق أزرق صغير", "حروف \"R.A.\"", "لا شيء، نظيفة تماماً", "لست متأكداً"),
        correctOptionIndex = 0
    )

    val demoOtherCandidates = listOf(
        MatchCandidate(
            id = "match-2",
            foundReport = ReportItem(
                id = "FND-8821",
                type = ReportType.Found,
                title = "White wireless earbuds case",
                titleAr = "حافظة سماعات لاسلكية بيضاء",
                category = "Earbuds case",
                categoryAr = "حافظة سماعات",
                color = "White",
                colorAr = "أبيض",
                brand = "Unknown",
                features = listOf("Minor wear"),
                location = "Central Library",
                locationAr = "المكتبة المركزية",
                zoneDetail = "Study cubicle 4",
                timeDescription = "Yesterday · 4:10 PM",
                description = "White earphone case found on study cubicle desk.",
                imageDrawableRes = R.drawable.img_airpods_found,
                status = CaseStatus.Searching,
                createdAt = "Yesterday, 4:10 PM"
            ),
            overallScore = 74,
            tier = MatchTier.Possible,
            visualScore = 78,
            descriptionScore = 72,
            attributesScore = 75,
            locationScore = 70,
            timeScore = 65,
            summaryEn = "Possible match. Similar appearance, but location differs from report.",
            summaryAr = "تطابق محتمل. المظهر متشابه ولكن الموقع يختلف عن البلاغ.",
            whyChipsEn = listOf("Same color", "Similar model"),
            whyChipsAr = listOf("نفس اللون", "موديل شبيه"),
            verificationQuestionEn = "Does the case have any marks?",
            verificationQuestionAr = "هل الحافظة بها علامات مميزة؟",
            optionsEn = listOf("No marks", "Small scratch", "Engraved name", "Not sure"),
            optionsAr = listOf("بلا علامات", "خدش صغير", "اسم محفور", "لست متأكداً"),
            correctOptionIndex = 1
        ),
        MatchCandidate(
            id = "match-3",
            foundReport = ReportItem(
                id = "FND-7643",
                type = ReportType.Found,
                title = "White charging case",
                titleAr = "حافظة شحن بيضاء",
                category = "Earbuds case",
                categoryAr = "حافظة سماعات",
                color = "White",
                colorAr = "أبيض",
                brand = "Generic",
                features = emptyList(),
                location = "Main Cafeteria",
                locationAr = "الكافيتريا الرئيسية",
                zoneDetail = "Corner table",
                timeDescription = "2 days ago · 1:30 PM",
                description = "Found white small charging case on dining table.",
                imageDrawableRes = R.drawable.img_airpods_found,
                status = CaseStatus.Searching,
                createdAt = "2 days ago"
            ),
            overallScore = 61,
            tier = MatchTier.LowConfidence,
            visualScore = 64,
            descriptionScore = 58,
            attributesScore = 60,
            locationScore = 62,
            timeScore = 50,
            summaryEn = "Low confidence match. Significant time difference.",
            summaryAr = "تطابق ضعيف الثقة لاختلاف كبير في وقت ومكان الفقد.",
            whyChipsEn = listOf("Same color"),
            whyChipsAr = listOf("نفس اللون"),
            verificationQuestionEn = "Brand of charging case?",
            verificationQuestionAr = "ماركة حافظة الشحن؟",
            optionsEn = listOf("Generic", "Apple", "Samsung", "Not sure"),
            optionsAr = listOf("عامة", "أبل", "سامسونج", "لست متأكداً"),
            correctOptionIndex = 0
        )
    )

    private val initialNotifications = listOf(
        NotificationItem(
            id = "notif-1",
            type = "match",
            titleEn = "Potential match found",
            titleAr = "تم العثور على تطابق محتمل",
            bodyEn = "Your item received a 93% Match Score.",
            bodyAr = "حصل غرضك على نسبة تطابق 93%.",
            time = "11:22 AM",
            isUnread = true,
            relatedCaseId = "MFQ-2841"
        ),
        NotificationItem(
            id = "notif-2",
            type = "verification",
            titleEn = "Ownership verification required",
            titleAr = "التحقق من الملكية مطلوب",
            bodyEn = "One quick question to confirm it's yours.",
            bodyAr = "سؤال سريع لتأكيد أن الغرض يخصك.",
            time = "11:25 AM",
            isUnread = true,
            relatedCaseId = "MFQ-2841"
        )
    )

    // State flows
    private val _activeReports = MutableStateFlow<List<ReportItem>>(listOf(initialLostCase))
    val activeReports: StateFlow<List<ReportItem>> = _activeReports.asStateFlow()

    private val _notifications = MutableStateFlow<List<NotificationItem>>(initialNotifications)
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    private val _currentActiveCase = MutableStateFlow<ReportItem>(initialLostCase)
    val currentActiveCase: StateFlow<ReportItem> = _currentActiveCase.asStateFlow()

    // Admin Stats
    private val _openReportsCount = MutableStateFlow(14)
    val openReportsCount: StateFlow<Int> = _openReportsCount.asStateFlow()

    private val _matchedCount = MutableStateFlow(8)
    val matchedCount: StateFlow<Int> = _matchedCount.asStateFlow()

    private val _claimedCount = MutableStateFlow(5)
    val claimedCount: StateFlow<Int> = _claimedCount.asStateFlow()

    private val _recoveredCount = MutableStateFlow(42)
    val recoveredCount: StateFlow<Int> = _recoveredCount.asStateFlow()

    fun updateCaseStatus(newStatus: CaseStatus) {
        val updated = _currentActiveCase.value.copy(
            status = newStatus,
            currentJourneyStep = newStatus.stepIndex
        )
        _currentActiveCase.value = updated
        _activeReports.value = _activeReports.value.map {
            if (it.id == updated.id) updated else it
        }

        if (newStatus == CaseStatus.Recovered) {
            _recoveredCount.value += 1
            val recoveryNotif = NotificationItem(
                id = "notif-rec-${System.currentTimeMillis()}",
                type = "recovery",
                titleEn = "Your item has been marked as recovered.",
                titleAr = "تم تسجيل استرداد غرضك بنجاح.",
                bodyEn = "Case MFQ-2841 is now closed.",
                bodyAr = "المعاملة MFQ-2841 أُغلقت بنجاح.",
                time = "Just now",
                isUnread = true,
                relatedCaseId = "MFQ-2841"
            )
            _notifications.value = listOf(recoveryNotif) + _notifications.value
        }
    }

    fun markCaseRecovered(caseId: String) {
        _activeReports.value = _activeReports.value.map {
            if (it.id == caseId || it.referenceCode == caseId) {
                it.copy(status = CaseStatus.Recovered, currentJourneyStep = 6)
            } else it
        }
        _recoveredCount.value += 1
    }

    fun addReport(newReport: ReportItem) {
        _currentActiveCase.value = newReport
        _activeReports.value = listOf(newReport) + _activeReports.value
        _openReportsCount.value += 1
    }

    fun markNotificationsAsRead() {
        _notifications.value = _notifications.value.map { it.copy(isUnread = false) }
    }

    fun resetToInitialDemoState() {
        _currentActiveCase.value = initialLostCase
        _activeReports.value = listOf(initialLostCase)
        _notifications.value = initialNotifications
        _recoveredCount.value = 42
    }
}
