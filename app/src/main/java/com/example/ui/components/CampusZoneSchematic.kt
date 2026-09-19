package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CampusBuilding
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.theme.BorderColor
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.CanvasBg
import com.example.ui.theme.Navy900
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

/**
 * Schematic Campus Zone Blocks Component (Spec S11)
 * Clean, schematic representation of campus sectors.
 * NOT a GPS map, strictly respecting student privacy.
 */
@Composable
fun CampusZoneSchematic(
    buildings: List<CampusBuilding>,
    selectedBuilding: CampusBuilding?,
    onBuildingSelected: (CampusBuilding) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CanvasBg)
            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isAr) "مخطط مناطق الحرم الجامعي (بيشة)" else "Campus Zone Schematic (UB)",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMuted
            )
            Text(
                text = if (isAr) "تقريبي فقط" else "Approximate Only",
                fontSize = 11.sp,
                color = Teal600,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3x3 Grid of schematic zones
        val gridItems = buildings.take(9)
        val chunked = gridItems.chunked(3)

        for (row in chunked) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (building in row) {
                    val isSelected = selectedBuilding?.id == building.id
                    val displayName = if (isAr) building.nameAr else building.nameEn

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(76.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) Teal600 else Color.White)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) Teal600 else BorderStrong,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable { onBuildingSelected(building) }
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = building.zone,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Teal50 else TextMuted
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = displayName,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (isSelected) Color.White else Navy900,
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
