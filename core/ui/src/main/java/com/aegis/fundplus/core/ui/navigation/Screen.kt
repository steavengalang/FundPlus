package com.aegis.fundplus.core.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Nexus", Icons.Default.Home)
    object Input : Screen("input", "Vault", Icons.Default.Add)
    object Gamification : Screen("gamification", "Savage", Icons.Default.Warning)
    object Export : Screen("export", "Audit", Icons.Default.List)
}
