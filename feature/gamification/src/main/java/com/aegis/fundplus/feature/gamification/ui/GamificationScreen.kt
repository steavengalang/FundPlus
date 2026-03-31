package com.aegis.fundplus.feature.gamification.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aegis.fundplus.core.ui.theme.PersonalityVibe

@Composable
fun GamificationScreen() {
    val currentVibe = PersonalityVibe.NEO_TOKYO // Default vibe

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))
            .padding(top = 48.dp, bottom = 100.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // AI Roast
        RoastMyWalletCard(
            currentVibe = currentVibe
        )

        // Hitung Mundur Kebangkrutan
        FinancialDeathClock(
            currentVibe = currentVibe,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
