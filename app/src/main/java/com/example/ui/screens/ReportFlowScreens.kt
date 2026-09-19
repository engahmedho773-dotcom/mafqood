package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.CampusBuilding
import com.example.data.ReportType
import com.example.ui.components.CampusZoneSchematic
import com.example.ui.components.PrimaryButton
import com.example.ui.components.SecondaryButton
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.i18n.Strings
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Signal50
import com.example.ui.theme.Signal600
import com.example.ui.theme.Success50
import com.example.ui.theme.Success600
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

// S08: Report Type Bottom Sheet
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportTypeSheet(
    onDismiss: () -> Unit,
    onSelectType: (ReportType) -> Unit
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
            Text(
                text = Strings.get("what_happened", lang),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )

            Spacer(modifier = Modifier.height(20.dp))

            // I lost something row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                    .clickable { onSelectType(ReportType.Lost) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Teal50),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Teal600)
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = Strings.get("i_lost_something", lang),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Navy900
                    )
                    Text(
                        text = Strings.get("i_lost_desc", lang),
                        fontSize = 13.sp,
                        color = TextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // I found something row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                    .clickable { onSelectType(ReportType.Found) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Signal50),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Signal600)
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = Strings.get("i_found_something", lang),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Navy900
                    )
                    Text(
                        text = Strings.get("i_found_desc", lang),
                        fontSize = 13.sp,
                        color = TextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

// 5-Segment Progress Bar Component (Spec 3.8)
@Composable
fun FlowProgressHeader(
    currentStep: Int, // 1..5
    totalSteps: Int = 5,
    onClose: () -> Unit,
    title: String = "Lost item"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp, bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Navy900)
            }
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )
            Text(
                text = "Step $currentStep of $totalSteps",
                fontSize = 13.sp,
                color = TextMuted
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 5-segment indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 1..totalSteps) {
                val isDone = i <= currentStep
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (isDone) Teal600 else BorderColor)
                )
            }
        }
    }
}

// S09: Step 1 · Photo
@Composable
fun Step1PhotoScreen(
    reportType: ReportType,
    hasPhoto: Boolean,
    onTakePhoto: () -> Unit,
    onContinue: () -> Unit,
    onClose: () -> Unit
) {
    val lang = LocalAppLanguage.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
    ) {
        FlowProgressHeader(
            currentStep = 1,
            title = if (reportType == ReportType.Lost) "Lost item" else "Found item",
            onClose = onClose
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = Strings.get("step_photo_title", lang),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = Strings.get("step_photo_sub", lang),
                    fontSize = 14.sp,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Photo Viewfinder / Preview
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Teal50)
                        .border(1.5.dp, if (hasPhoto) Teal600 else Teal50, RoundedCornerShape(24.dp))
                        .clickable { onTakePhoto() },
                    contentAlignment = Alignment.Center
                ) {
                    if (hasPhoto) {
                        Image(
                            painter = painterResource(
                                id = if (reportType == ReportType.Lost) R.drawable.img_airpods_lost else R.drawable.img_airpods_found
                            ),
                            contentDescription = "Captured photo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        // Retake chip bottom end
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Navy900.copy(alpha = 0.75f))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text("Retake", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Default.CameraAlt,
                                contentDescription = null,
                                tint = Teal600,
                                modifier = Modifier.size(38.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Tap to take a photo",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Teal600
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Action Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrimaryButton(
                        text = Strings.get("take_photo", lang),
                        onClick = onTakePhoto,
                        modifier = Modifier.weight(1f),
                        height = 48.dp
                    )
                    SecondaryButton(
                        text = Strings.get("from_gallery", lang),
                        onClick = onTakePhoto,
                        modifier = Modifier.weight(1f),
                        height = 48.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // If photo taken: "MAFQOOD sees" AI pre-fill chips
            if (hasPhoto) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Teal600, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = Strings.get("mafqood_sees", lang),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Teal600
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SuggestedTag("White")
                            SuggestedTag("Earbuds case")
                            SuggestedTag("Apple")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Success600, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = Strings.get("photo_clear", lang),
                                fontSize = 12.sp,
                                color = Success600,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Tips Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "💡 " + Strings.get("tip_light", lang), fontSize = 12.sp, color = TextMuted)
                    Text(text = "📐 " + Strings.get("tip_bg", lang), fontSize = 12.sp, color = TextMuted)
                    Text(text = "🔍 " + Strings.get("tip_marks", lang), fontSize = 12.sp, color = TextMuted)
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(
                    onClick = onTakePhoto,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = Strings.get("describe_instead", lang),
                        fontSize = 13.sp,
                        color = Teal600
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
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
                text = Strings.get("continue", lang),
                onClick = onContinue,
                enabled = hasPhoto,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SuggestedTag(text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Teal50)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 11.sp, color = Teal600, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "★", fontSize = 9.sp, color = Signal600)
    }
}

// S10: Step 2 · Details
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step2DetailsScreen(
    reportType: ReportType,
    category: String,
    onCategoryChange: (String) -> Unit,
    color: String,
    onColorChange: (String) -> Unit,
    brand: String,
    onBrandChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    secretClue: String,
    onSecretClueChange: (String) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onClose: () -> Unit
) {
    val lang = LocalAppLanguage.current
    var showCategorySheet by remember { mutableStateOf(false) }

    val colorsList = listOf("White", "Black", "Grey", "Blue", "Red", "Green", "Yellow", "Other")
    val featureChips = listOf("Scratch", "Sticker", "Engraving", "Keychain", "Crack", "Case cover")
    var selectedFeatures by remember { mutableStateOf(setOf("Scratch")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
    ) {
        FlowProgressHeader(
            currentStep = 2,
            title = if (reportType == ReportType.Lost) "Lost item" else "Found item",
            onClose = onClose
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            // Header Summary
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White)
                        .border(1.dp, BorderColor, RoundedCornerShape(14.dp))
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            id = if (reportType == ReportType.Lost) R.drawable.img_airpods_lost else R.drawable.img_airpods_found
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "White AirPods case",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Navy900
                        )
                        Text(
                            text = Strings.get("step_details_sub", lang),
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Category selector
            item {
                Text(text = Strings.get("category", lang), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Navy900)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.5.dp, BorderStrong, RoundedCornerShape(12.dp))
                        .clickable { showCategorySheet = true }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = category, fontSize = 15.sp, color = Navy900, fontWeight = FontWeight.Medium)
                    SuggestedTag("Suggested")
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Color swatches row
            item {
                Text(text = Strings.get("color", lang), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Navy900)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    colorsList.take(6).forEach { col ->
                        val isSelected = color == col
                        val swatchColor = when (col) {
                            "White" -> Color.White
                            "Black" -> Color.Black
                            "Grey" -> Color.Gray
                            "Blue" -> Color(0xFF1976D2)
                            "Red" -> Color(0xFFD32F2F)
                            else -> Color(0xFF388E3C)
                        }
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(swatchColor)
                                .border(
                                    width = if (isSelected) 2.5.dp else 1.dp,
                                    color = if (isSelected) Teal600 else BorderStrong,
                                    shape = CircleShape
                                )
                                .clickable { onColorChange(col) },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = null,
                                    tint = if (col == "White") Teal600 else Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Brand autocomplete input
            item {
                Text(text = Strings.get("brand", lang), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Navy900)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = brand,
                    onValueChange = onBrandChange,
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
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Distinctive features
            item {
                Text(text = Strings.get("distinctive_features", lang), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Navy900)
                Text(text = Strings.get("distinctive_features_sub", lang), fontSize = 12.sp, color = TextMuted)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    featureChips.take(4).forEach { feat ->
                        val isSelected = selectedFeatures.contains(feat)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) Teal50 else Color.White)
                                .border(1.dp, if (isSelected) Teal600 else BorderStrong, RoundedCornerShape(16.dp))
                                .clickable {
                                    selectedFeatures = if (isSelected) selectedFeatures - feat else selectedFeatures + feat
                                }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = feat,
                                fontSize = 12.sp,
                                color = if (isSelected) Teal600 else Navy900,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // If Found flow: Secret Clue field
            if (reportType == ReportType.Found) {
                item {
                    Text(
                        text = Strings.get("secret_verification_field", lang),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Navy900
                    )
                    Text(
                        text = Strings.get("secret_verification_sub", lang),
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = secretClue,
                        onValueChange = onSecretClueChange,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = Signal600, modifier = Modifier.size(18.dp))
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Teal600,
                            unfocusedBorderColor = BorderStrong,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }

            // Description textarea
            item {
                Text(text = Strings.get("description", lang), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Navy900)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    placeholder = { Text(Strings.get("description_placeholder", lang), fontSize = 13.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Teal600,
                        unfocusedBorderColor = BorderStrong,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Sticky Footer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                text = Strings.get("back", lang),
                onClick = onBack,
                modifier = Modifier.width(96.dp),
                height = 56.dp
            )
            PrimaryButton(
                text = Strings.get("continue", lang),
                onClick = onContinue,
                modifier = Modifier.weight(1f)
            )
        }
    }

    // Category Selector Sheet
    if (showCategorySheet) {
        val categories = listOf("Earbuds case", "Phone", "Keys", "Wallet", "ID card", "Laptop", "Bag", "Other")
        ModalBottomSheet(
            onDismissRequest = { showCategorySheet = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Select Category", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Navy900)
                Spacer(modifier = Modifier.height(16.dp))
                categories.forEach { cat ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCategoryChange(cat)
                                showCategorySheet = false
                            }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(cat, fontSize = 16.sp, color = Navy900)
                        if (category == cat) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Teal600)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// S11: Step 3 · Where (List + Schematic Zone Map)
@Composable
fun Step3WhereScreen(
    reportType: ReportType,
    buildings: List<CampusBuilding>,
    selectedBuilding: CampusBuilding?,
    onSelectBuilding: (CampusBuilding) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onClose: () -> Unit
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC
    var viewMode by remember { mutableStateOf(0) } // 0 = List, 1 = Zones
    var searchQuery by remember { mutableStateOf("") }

    val filteredBuildings = buildings.filter {
        it.nameEn.contains(searchQuery, ignoreCase = true) || it.nameAr.contains(searchQuery)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
    ) {
        FlowProgressHeader(
            currentStep = 3,
            title = if (reportType == ReportType.Lost) "Lost item" else "Found item",
            onClose = onClose
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (reportType == ReportType.Lost) Strings.get("step_where_title", lang) else Strings.get("where_found_title", lang),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = TextMuted, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = Strings.get("where_privacy_note", lang),
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Segmented toggle: List | Zones
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (viewMode == 0) Teal600 else Color.Transparent)
                            .clickable { viewMode = 0 },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.get("list_view", lang),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (viewMode == 0) Color.White else Navy900
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (viewMode == 1) Teal600 else Color.Transparent)
                            .clickable { viewMode = 1 },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.get("zones_view", lang),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (viewMode == 1) Color.White else Navy900
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (viewMode == 0) {
                // Search Input
                item {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text(Strings.get("search_building", lang), fontSize = 13.sp) },
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
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Buildings List
                items(filteredBuildings.size) { idx ->
                    val bldg = filteredBuildings[idx]
                    val isSelected = selectedBuilding?.id == bldg.id
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) Teal50 else Color.White)
                            .border(1.dp, if (isSelected) Teal600 else BorderColor, RoundedCornerShape(12.dp))
                            .clickable { onSelectBuilding(bldg) }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = if (isAr) bldg.nameAr else bldg.nameEn,
                                fontSize = 15.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = Navy900
                            )
                            Text(
                                text = bldg.zone,
                                fontSize = 12.sp,
                                color = TextMuted
                            )
                        }
                        if (isSelected) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Teal600)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                // Schematic Block Map
                item {
                    CampusZoneSchematic(
                        buildings = buildings,
                        selectedBuilding = selectedBuilding,
                        onBuildingSelected = onSelectBuilding
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Floor Refine Chips if building selected
            if (selectedBuilding != null) {
                item {
                    Text(
                        text = "Specific Area (Optional)",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Navy900
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        selectedBuilding.floorOptions.take(4).forEach { option ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color.White)
                                    .border(1.dp, BorderStrong, RoundedCornerShape(14.dp))
                                    .clickable { }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(option, fontSize = 12.sp, color = Navy900)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

        // Sticky Footer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                text = Strings.get("back", lang),
                onClick = onBack,
                modifier = Modifier.width(96.dp),
                height = 56.dp
            )
            PrimaryButton(
                text = Strings.get("continue", lang),
                onClick = onContinue,
                enabled = selectedBuilding != null,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// S12: Step 4 · When (Slider & Quick Chips)
@Composable
fun Step4WhenScreen(
    reportType: ReportType,
    timeDescription: String,
    onTimeChange: (String) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onClose: () -> Unit
) {
    val lang = LocalAppLanguage.current
    var sliderVal by remember { mutableFloatStateOf(11.0f) }
    var selectedQuickChip by remember { mutableStateOf("Earlier today") }

    val quickChips = listOf("Just now", "Earlier today", "Yesterday", "Pick a date")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
    ) {
        FlowProgressHeader(
            currentStep = 4,
            title = if (reportType == ReportType.Lost) "Lost item" else "Found item",
            onClose = onClose
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (reportType == ReportType.Lost) Strings.get("step_when_title", lang) else Strings.get("when_found_title", lang),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Navy900
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Quick Chips Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                quickChips.forEach { chip ->
                    val isSelected = selectedQuickChip == chip
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) Teal50 else Color.White)
                            .border(1.dp, if (isSelected) Teal600 else BorderStrong, RoundedCornerShape(16.dp))
                            .clickable { selectedQuickChip = chip }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = chip,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            color = if (isSelected) Teal600 else Navy900
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Big Readout Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .border(1.dp, BorderColor, RoundedCornerShape(20.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val hour = sliderVal.toInt()
                val minutes = if ((sliderVal - hour) >= 0.5f) "30" else "00"
                val ampm = if (hour >= 12) "PM" else "AM"
                val displayHour = if (hour > 12) hour - 12 else if (hour == 0) 12 else hour
                val timeString = "About $displayHour:$minutes $ampm"

                Text(
                    text = timeString,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "± 30 minutes window",
                    fontSize = 14.sp,
                    color = TextMuted
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Timeline slider (6 AM to 10 PM)
                Slider(
                    value = sliderVal,
                    onValueChange = {
                        sliderVal = it
                        onTimeChange(timeString)
                    },
                    valueRange = 6f..22f,
                    steps = 31,
                    colors = SliderDefaults.colors(
                        thumbColor = Teal600,
                        activeTrackColor = Teal600,
                        inactiveTrackColor = BorderColor
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("06:00 AM", fontSize = 11.sp, color = TextMuted)
                    Text("12:00 PM", fontSize = 11.sp, color = TextMuted)
                    Text("10:00 PM", fontSize = 11.sp, color = TextMuted)
                }
            }
        }

        // Sticky Footer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                text = Strings.get("back", lang),
                onClick = onBack,
                modifier = Modifier.width(96.dp),
                height = 56.dp
            )
            PrimaryButton(
                text = Strings.get("continue", lang),
                onClick = onContinue,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// S13: Step 5 · Review & Submit
@Composable
fun Step5ReviewScreen(
    reportType: ReportType,
    category: String,
    color: String,
    brand: String,
    location: String,
    timeDescription: String,
    description: String,
    whereIsItNow: String,
    onWhereIsItNowChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    onClose: () -> Unit
) {
    val lang = LocalAppLanguage.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CanvasBg)
    ) {
        FlowProgressHeader(
            currentStep = 5,
            title = if (reportType == ReportType.Lost) "Lost item" else "Found item",
            onClose = onClose
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = Strings.get("step_review_title", lang),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Navy900
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // If found item: "Where is it now?" block
            if (reportType == ReportType.Found) {
                item {
                    Text(
                        text = Strings.get("where_is_it_now", lang),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Navy900
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    val options = listOf(
                        "I handed it to Campus Security",
                        "I left it at a drop point",
                        "I'm keeping it with me"
                    )

                    options.forEach { opt ->
                        val isSelected = whereIsItNow == opt
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) Teal50 else Color.White)
                                .border(1.dp, if (isSelected) Teal600 else BorderColor, RoundedCornerShape(12.dp))
                                .clickable { onWhereIsItNowChange(opt) }
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { onWhereIsItNowChange(opt) },
                                colors = RadioButtonDefaults.colors(selectedColor = Teal600)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = opt, fontSize = 14.sp, color = Navy900)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            // Summary Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(
                                    id = if (reportType == ReportType.Lost) R.drawable.img_airpods_lost else R.drawable.img_airpods_found
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = "$color $brand $category",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = Navy900
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (reportType == ReportType.Lost) "Lost report" else "Found report",
                                    fontSize = 12.sp,
                                    color = TextMuted
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        ReviewItemRow("Category", category)
                        ReviewItemRow("Color", color)
                        ReviewItemRow("Brand", brand)
                        ReviewItemRow("Place", location)
                        ReviewItemRow("Time", timeDescription)
                        if (description.isNotEmpty()) {
                            ReviewItemRow("Details", description)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Privacy note
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = Strings.get("privacy_review_note", lang),
                        fontSize = 12.sp,
                        color = TextMuted,
                        lineHeight = 17.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Sticky Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, BorderColor)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = Strings.get("search_duration_hint", lang),
                fontSize = 12.sp,
                color = TextMuted,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )
            PrimaryButton(
                text = if (reportType == ReportType.Lost) Strings.get("find_my_item", lang) else Strings.get("report_found_item", lang),
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ReviewItemRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 13.sp, color = TextMuted)
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Navy900)
    }
}
