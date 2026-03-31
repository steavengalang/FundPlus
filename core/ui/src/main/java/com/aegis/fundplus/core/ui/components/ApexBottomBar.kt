package com.aegis.fundplus.core.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aegis.fundplus.core.ui.navigation.Screen

@Composable
fun ApexBottomBar(navController: NavHostController) {
    val items = listOf(
        Screen.Dashboard,
        Screen.Input,
        Screen.Gamification,
        Screen.Export
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Dashboard.route

    // Container Melayang ala Glassmorphism Apple
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xE60A0A0A)) // Obsidian Dark + Blur Alpha
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screen ->
                val isSelected = currentRoute == screen.route
                ApexBottomBarItem(screen = screen, isSelected = isSelected) {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Composable
fun ApexBottomBarItem(
    screen: Screen,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Animasi Sentuh
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.25f else 1.0f,
        animationSpec = tween(durationMillis = 300),
        label = "nav_scale"
    )
    val tintColor = if (isSelected) Color(0xFF00E5FF) else Color(0xFF757575)

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = screen.icon,
            contentDescription = screen.title,
            tint = tintColor,
            modifier = Modifier
                .size(26.dp)
                .graphicsLayer(scaleX = scale, scaleY = scale)
        )
        if (isSelected) {
            Text(
                text = screen.title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = tintColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
