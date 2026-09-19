package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.i18n.AppLanguage
import com.example.ui.i18n.LocalAppLanguage
import com.example.ui.theme.BorderColor
import com.example.ui.theme.Navy900
import com.example.ui.theme.Teal50
import com.example.ui.theme.Teal600
import com.example.ui.theme.TextMuted

enum class MafqoodTab {
    Home,
    Cases,
    Profile
}

@Composable
fun MafqoodBottomBar(
    selectedTab: MafqoodTab,
    onTabSelected: (MafqoodTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = LocalAppLanguage.current
    val isAr = lang == AppLanguage.ARABIC

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .border(1.dp, BorderColor),
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        // Home
        NavigationBarItem(
            selected = selectedTab == MafqoodTab.Home,
            onClick = { onTabSelected(MafqoodTab.Home) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == MafqoodTab.Home) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = if (isAr) "الرئيسية" else "Home",
                    fontSize = 12.sp,
                    fontWeight = if (selectedTab == MafqoodTab.Home) FontWeight.SemiBold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Teal600,
                selectedTextColor = Teal600,
                indicatorColor = Teal50,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted
            )
        )

        // Cases
        NavigationBarItem(
            selected = selectedTab == MafqoodTab.Cases,
            onClick = { onTabSelected(MafqoodTab.Cases) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == MafqoodTab.Cases) Icons.Filled.Folder else Icons.Outlined.Folder,
                    contentDescription = "Cases",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = if (isAr) "البلاغات" else "Cases",
                    fontSize = 12.sp,
                    fontWeight = if (selectedTab == MafqoodTab.Cases) FontWeight.SemiBold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Teal600,
                selectedTextColor = Teal600,
                indicatorColor = Teal50,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted
            )
        )

        // Profile
        NavigationBarItem(
            selected = selectedTab == MafqoodTab.Profile,
            onClick = { onTabSelected(MafqoodTab.Profile) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == MafqoodTab.Profile) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = if (isAr) "حسابي" else "Profile",
                    fontSize = 12.sp,
                    fontWeight = if (selectedTab == MafqoodTab.Profile) FontWeight.SemiBold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Teal600,
                selectedTextColor = Teal600,
                indicatorColor = Teal50,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted
            )
        )
    }
}
