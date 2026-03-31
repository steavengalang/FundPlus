package com.aegis.fundplus.feature.gamification.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import com.aegis.fundplus.core.ui.theme.PersonalityVibe
import com.aegis.fundplus.core.ui.theme.VibeEngine

@Composable
fun RoastMyWalletCard(
    currentVibe: PersonalityVibe,
    savageMessage: String = "Tiga ratus ribu buat gacha lagi? Udah dibilangin dari minggu lalu, cicilan KPR aja belum tembus, Bang!",
    modifier: Modifier = Modifier
) {
    val colors = VibeEngine.getColors(currentVibe)
    val typo = VibeEngine.getTypography(currentVibe)

    // Deteksi jika tema sedang Neo-Tokyo, background agak transparan supaya nyala (Glitch)
    val bgAlpha = if (currentVibe == PersonalityVibe.NEO_TOKYO) 0.8f else 1.0f
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.accentThreat.copy(alpha = bgAlpha))
            .border(
                width = if (currentVibe == PersonalityVibe.KAWAII_OVERLOAD) 4.dp else 1.dp,
                color = colors.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            
            // Header Sarkas
            Text(
                text = "🔥 ROAST MY WALLET 🔥",
                style = typo.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold, 
                    color = colors.textMain,
                    letterSpacing = 2.sp
                ),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // The Savage Text
            Text(
                text = "\"$savageMessage\"",
                style = typo.bodyLarge.copy(color = colors.surface), // Contrast Text
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
