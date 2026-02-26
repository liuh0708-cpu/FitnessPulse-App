package com.example.liuhaoyangsapplication.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun UtilityApp() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf(
        NavigationItem("Home", Icons.Filled.Home),
        NavigationItem("Settings", Icons.Filled.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedItem) {
            0 -> UtilityScreen(modifier = Modifier.padding(innerPadding))
            1 -> SettingsScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector
)