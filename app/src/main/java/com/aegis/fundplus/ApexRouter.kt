package com.aegis.fundplus

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aegis.fundplus.core.ui.components.ApexBottomBar
import com.aegis.fundplus.core.ui.navigation.Screen
import com.aegis.fundplus.feature.dashboard.DashboardViewModel
import com.aegis.fundplus.feature.dashboard.ui.DashboardScreen
import com.aegis.fundplus.feature.input.ui.InputScreen
import com.aegis.fundplus.feature.gamification.ui.GamificationScreen
import com.aegis.fundplus.feature.export.ExportViewModel
import com.aegis.fundplus.feature.export.ui.ExportScreen

@Composable
fun ApexRouter() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { ApexBottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                // Hilt DI otomatis menyuntikkan DashboardViewModel
                val viewModel: DashboardViewModel = hiltViewModel()
                DashboardScreen(viewModel = viewModel)
            }
            
            composable(Screen.Input.route) {
                InputScreen()
            }
            
            composable(Screen.Gamification.route) {
                GamificationScreen()
            }
            
            composable(Screen.Export.route) {
                val viewModel: ExportViewModel = hiltViewModel()
                ExportScreen(viewModel = viewModel)
            }
        }
    }
}
