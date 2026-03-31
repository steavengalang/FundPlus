package com.aegis.fundplus.feature.gamification.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GamificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A)) // Mode Gelap Ekstrem
            .padding(top = 48.dp, bottom = 100.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // AI Roast
        RoastMyWalletCard(
            roastMessage = "Saldo Anda menipis. Tolong berhenti membeli kopi harga 50 ribu setiap hari."
        )

        // Hitung Mundur Kebangkrutan
        FinancialDeathClock(
            daysLeft = 14,
            burnRate = 150000.0,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
