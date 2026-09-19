package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.data.CampusBuilding
import com.example.data.CaseStatus
import com.example.data.MafqoodRepository
import com.example.data.MatchCandidate
import com.example.data.ReportItem
import com.example.data.ReportType
import com.example.ui.components.MafqoodBottomBar
import com.example.ui.components.MafqoodTab
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.screens.AiAnalysisScreen
import com.example.ui.screens.AllCasesScreen
import com.example.ui.screens.CaseDetailScreen
import com.example.ui.screens.ClaimIntroSheet
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.MatchDetailScreen
import com.example.ui.screens.MatchResultsScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.OwnershipVerifiedScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.RecoveredScreen
import com.example.ui.screens.RecoveryArrangementScreen
import com.example.ui.screens.ReportTypeSheet
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.Step1PhotoScreen
import com.example.ui.screens.Step2DetailsScreen
import com.example.ui.screens.Step3WhereScreen
import com.example.ui.screens.Step4WhenScreen
import com.example.ui.screens.Step5ReviewScreen
import com.example.ui.screens.VerificationQuestionScreen
import com.example.ui.theme.MyApplicationTheme

sealed interface Screen {
    data object Splash : Screen
    data object Onboarding : Screen
    data object Login : Screen
    data object Main : Screen
    data class CaseDetail(val item: ReportItem) : Screen
    data object AllCases : Screen
    data class ReportStep1(val type: ReportType) : Screen
    data class ReportStep2(val type: ReportType) : Screen
    data class ReportStep3(val type: ReportType) : Screen
    data class ReportStep4(val type: ReportType) : Screen
    data class ReportStep5(val type: ReportType) : Screen
    data class AiAnalysis(val type: ReportType) : Screen
    data class MatchResults(val bestMatch: MatchCandidate, val otherMatches: List<MatchCandidate>) : Screen
    data class MatchDetail(val match: MatchCandidate, val lostReport: ReportItem) : Screen
    data class VerificationQuestion(val match: MatchCandidate) : Screen
    data object OwnershipVerified : Screen
    data object RecoveryArrangement : Screen
    data object Recovered : Screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MafqoodApp()
        }
    }
}

@Composable
fun MafqoodApp() {
    var appLanguage by remember { mutableStateOf(AppLanguage.ENGLISH) }

    CompositionLocalProvider(LocalAppLanguage provides appLanguage) {
        MyApplicationTheme {
            val activeReports by MafqoodRepository.activeReports.collectAsState()
            val buildings = remember { MafqoodRepository.campusBuildings }
            val bestCandidate = remember { MafqoodRepository.demoMatchCandidate }
            val otherCandidates = remember { MafqoodRepository.demoOtherCandidates }

            // Navigation backstack
            val backStack = remember { mutableStateListOf<Screen>(Screen.Splash) }
            val currentScreen = backStack.lastOrNull() ?: Screen.Main

            fun navigateTo(screen: Screen) {
                backStack.add(screen)
            }

            fun popBack() {
                if (backStack.size > 1) {
                    backStack.removeAt(backStack.lastIndex)
                }
            }

            fun popToMain() {
                while (backStack.size > 1 && backStack.last() != Screen.Main) {
                    backStack.removeAt(backStack.lastIndex)
                }
                if (backStack.isEmpty() || backStack.last() != Screen.Main) {
                    backStack.clear()
                    backStack.add(Screen.Main)
                }
            }

            // Bottom Navigation Tab State
            var selectedTab by remember { mutableStateOf(MafqoodTab.Home) }

            // Report creation form state
            var reportType by remember { mutableStateOf(ReportType.Lost) }
            var showReportTypeSheet by remember { mutableStateOf(false) }
            var showClaimIntroSheet by remember { mutableStateOf(false) }
            var pendingMatchForClaim by remember { mutableStateOf<MatchCandidate?>(null) }

            var formHasPhoto by remember { mutableStateOf(true) }
            var formCategory by remember { mutableStateOf("Earbuds case") }
            var formColor by remember { mutableStateOf("White") }
            var formBrand by remember { mutableStateOf("Apple") }
            var formDescription by remember { mutableStateOf("Lost near College of Science bench after 10 AM class.") }
            var formSelectedBuilding by remember { mutableStateOf<CampusBuilding?>(buildings.firstOrNull()) }
            var formTime by remember { mutableStateOf("About 11:00 AM") }
            var formWhereIsItNow by remember { mutableStateOf("I handed it to Campus Security") }
            var formSecretClue by remember { mutableStateOf("Blue star sticker inside lid") }

            BackHandler(enabled = backStack.size > 1) {
                popBack()
            }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    if (currentScreen is Screen.Main) {
                        MafqoodBottomBar(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = if (currentScreen is Screen.Main) innerPadding.calculateBottomPadding() else androidx.compose.ui.unit.Dp.Hairline)
                ) {
                    AnimatedContent(
                        targetState = currentScreen,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "screen_transition"
                    ) { screen ->
                        when (screen) {
                            is Screen.Splash -> {
                                SplashScreen(
                                    onTimeout = {
                                        backStack.clear()
                                        backStack.add(Screen.Onboarding)
                                    }
                                )
                            }
                            is Screen.Onboarding -> {
                                OnboardingScreen(
                                    onFinish = {
                                        backStack.clear()
                                        backStack.add(Screen.Login)
                                    }
                                )
                            }
                            is Screen.Login -> {
                                LoginScreen(
                                    onSignInSuccess = {
                                        backStack.clear()
                                        backStack.add(Screen.Main)
                                    },
                                    onHowItWorksClick = {
                                        navigateTo(Screen.Onboarding)
                                    }
                                )
                            }
                            is Screen.Main -> {
                                when (selectedTab) {
                                    MafqoodTab.Home -> {
                                        HomeScreen(
                                            activeReports = activeReports,
                                            onLostClick = {
                                                reportType = ReportType.Lost
                                                navigateTo(Screen.ReportStep1(ReportType.Lost))
                                            },
                                            onFoundClick = {
                                                reportType = ReportType.Found
                                                navigateTo(Screen.ReportStep1(ReportType.Found))
                                            },
                                            onReviewMatchClick = {
                                                navigateTo(Screen.MatchResults(bestCandidate, otherCandidates))
                                            },
                                            onCaseClick = { reportItem ->
                                                navigateTo(Screen.CaseDetail(reportItem))
                                            },
                                            onSeeAllClick = {
                                                navigateTo(Screen.AllCases)
                                            }
                                        )
                                    }
                                    MafqoodTab.Cases -> {
                                        AllCasesScreen(
                                            cases = activeReports,
                                            onCaseClick = { reportItem ->
                                                navigateTo(Screen.CaseDetail(reportItem))
                                            },
                                            onBack = { selectedTab = MafqoodTab.Home }
                                        )
                                    }
                                    MafqoodTab.Profile -> {
                                        ProfileScreen(
                                            onToggleLanguage = {
                                                appLanguage = if (appLanguage == AppLanguage.ENGLISH) AppLanguage.ARABIC else AppLanguage.ENGLISH
                                            },
                                            onSignOut = {
                                                backStack.clear()
                                                backStack.add(Screen.Login)
                                            }
                                        )
                                    }
                                }
                            }
                            is Screen.CaseDetail -> {
                                CaseDetailScreen(
                                    caseItem = screen.item,
                                    onViewMatch = {
                                        navigateTo(Screen.MatchResults(bestCandidate, otherCandidates))
                                    },
                                    onBack = { popBack() }
                                )
                            }
                            is Screen.AllCases -> {
                                AllCasesScreen(
                                    cases = activeReports,
                                    onCaseClick = { reportItem ->
                                        navigateTo(Screen.CaseDetail(reportItem))
                                    },
                                    onBack = { popBack() }
                                )
                            }
                            // 5-Step Report Flow
                            is Screen.ReportStep1 -> {
                                Step1PhotoScreen(
                                    reportType = screen.type,
                                    hasPhoto = formHasPhoto,
                                    onTakePhoto = { formHasPhoto = true },
                                    onContinue = { navigateTo(Screen.ReportStep2(screen.type)) },
                                    onClose = { popToMain() }
                                )
                            }
                            is Screen.ReportStep2 -> {
                                Step2DetailsScreen(
                                    reportType = screen.type,
                                    category = formCategory,
                                    onCategoryChange = { formCategory = it },
                                    color = formColor,
                                    onColorChange = { formColor = it },
                                    brand = formBrand,
                                    onBrandChange = { formBrand = it },
                                    description = formDescription,
                                    onDescriptionChange = { formDescription = it },
                                    secretClue = formSecretClue,
                                    onSecretClueChange = { formSecretClue = it },
                                    onContinue = { navigateTo(Screen.ReportStep3(screen.type)) },
                                    onBack = { popBack() },
                                    onClose = { popToMain() }
                                )
                            }
                            is Screen.ReportStep3 -> {
                                Step3WhereScreen(
                                    reportType = screen.type,
                                    buildings = buildings,
                                    selectedBuilding = formSelectedBuilding,
                                    onSelectBuilding = { formSelectedBuilding = it },
                                    onContinue = { navigateTo(Screen.ReportStep4(screen.type)) },
                                    onBack = { popBack() },
                                    onClose = { popToMain() }
                                )
                            }
                            is Screen.ReportStep4 -> {
                                Step4WhenScreen(
                                    reportType = screen.type,
                                    timeDescription = formTime,
                                    onTimeChange = { formTime = it },
                                    onContinue = { navigateTo(Screen.ReportStep5(screen.type)) },
                                    onBack = { popBack() },
                                    onClose = { popToMain() }
                                )
                            }
                            is Screen.ReportStep5 -> {
                                Step5ReviewScreen(
                                    reportType = screen.type,
                                    category = formCategory,
                                    color = formColor,
                                    brand = formBrand,
                                    location = formSelectedBuilding?.nameEn ?: "Science College",
                                    timeDescription = formTime,
                                    description = formDescription,
                                    whereIsItNow = formWhereIsItNow,
                                    onWhereIsItNowChange = { formWhereIsItNow = it },
                                    onSubmit = {
                                        // Add to repository
                                        MafqoodRepository.addReport(
                                            ReportItem(
                                                id = "rpt_${System.currentTimeMillis()}",
                                                type = screen.type,
                                                title = "$formColor $formBrand $formCategory",
                                                titleAr = "سماعة $formBrand $formColor",
                                                category = formCategory,
                                                color = formColor,
                                                brand = formBrand,
                                                location = formSelectedBuilding?.nameEn ?: "Science College",
                                                locationAr = formSelectedBuilding?.nameAr ?: "كلية العلوم",
                                                timeDescription = formTime,
                                                status = if (screen.type == ReportType.Lost) CaseStatus.MatchFound else CaseStatus.Searching,
                                                imageDrawableRes = if (screen.type == ReportType.Lost) com.example.R.drawable.img_airpods_lost else com.example.R.drawable.img_airpods_found,
                                                referenceCode = "MFQ-${(1000..9999).random()}",
                                                currentJourneyStep = if (screen.type == ReportType.Lost) 3 else 1
                                            )
                                        )
                                        navigateTo(Screen.AiAnalysis(screen.type))
                                    },
                                    onBack = { popBack() },
                                    onClose = { popToMain() }
                                )
                            }
                            is Screen.AiAnalysis -> {
                                AiAnalysisScreen(
                                    onSeeMatches = {
                                        navigateTo(Screen.MatchResults(bestCandidate, otherCandidates))
                                    },
                                    onBackgroundClick = {
                                        popToMain()
                                    }
                                )
                            }
                            is Screen.MatchResults -> {
                                MatchResultsScreen(
                                    bestMatch = screen.bestMatch,
                                    otherMatches = screen.otherMatches,
                                    onSelectMatch = { matchCandidate ->
                                        navigateTo(Screen.MatchDetail(matchCandidate, MafqoodRepository.activeReports.value.first()))
                                    },
                                    onBack = { popToMain() }
                                )
                            }
                            is Screen.MatchDetail -> {
                                MatchDetailScreen(
                                    match = screen.match,
                                    lostReport = screen.lostReport,
                                    onStartClaim = {
                                        pendingMatchForClaim = screen.match
                                        showClaimIntroSheet = true
                                    },
                                    onNotMine = {
                                        popBack()
                                    },
                                    onBack = { popBack() }
                                )
                            }
                            is Screen.VerificationQuestion -> {
                                VerificationQuestionScreen(
                                    match = screen.match,
                                    onVerified = {
                                        navigateTo(Screen.OwnershipVerified)
                                    },
                                    onBack = { popBack() }
                                )
                            }
                            is Screen.OwnershipVerified -> {
                                OwnershipVerifiedScreen(
                                    onArrangePickup = {
                                        navigateTo(Screen.RecoveryArrangement)
                                    }
                                )
                            }
                            is Screen.RecoveryArrangement -> {
                                RecoveryArrangementScreen(
                                    onConfirmCollected = {
                                        MafqoodRepository.markCaseRecovered("MFQ-2841")
                                        navigateTo(Screen.Recovered)
                                    },
                                    onBack = { popBack() }
                                )
                            }
                            is Screen.Recovered -> {
                                RecoveredScreen(
                                    onBackHome = { popToMain() },
                                    onViewSummary = {
                                        val recoveredCase = MafqoodRepository.activeReports.value.firstOrNull { it.referenceCode == "MFQ-2841" }
                                        if (recoveredCase != null) {
                                            navigateTo(Screen.CaseDetail(recoveredCase))
                                        } else {
                                            popToMain()
                                        }
                                    }
                                )
                            }
                        }
                    }

                    // Sheets overlays
                    if (showReportTypeSheet) {
                        ReportTypeSheet(
                            onDismiss = { showReportTypeSheet = false },
                            onSelectType = { type ->
                                showReportTypeSheet = false
                                reportType = type
                                navigateTo(Screen.ReportStep1(type))
                            }
                        )
                    }

                    if (showClaimIntroSheet) {
                        ClaimIntroSheet(
                            onDismiss = { showClaimIntroSheet = false },
                            onContinue = {
                                showClaimIntroSheet = false
                                pendingMatchForClaim?.let {
                                    navigateTo(Screen.VerificationQuestion(it))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
