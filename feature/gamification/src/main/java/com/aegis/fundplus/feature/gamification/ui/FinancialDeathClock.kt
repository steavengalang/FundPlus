package com.aegis.fundplus.feature.gamification.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aegis.fundplus.core.ui.theme.PersonalityVibe
import com.aegis.fundplus.core.ui.theme.VibeEngine
import kotlinx.coroutines.delay

@Composable
fun FinancialDeathClock(
    currentVibe: PersonalityVibe,
    daysLeftToBroke: Int = 4,
    hoursLeft: Int = 12,
    minsLeft: Int = 30,
    modifier: Modifier = Modifier
) {
    val colors = VibeEngine.getColors(currentVibe)

    // Kedip Animasi Jantung (Pulsing Alarm)
    val infiniteTransition = rememberInfiniteTransition(label = "death_clock_pulse")
    val alphaAnim by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha_pulse"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black) // Digital Clock selalu gelap
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val title = if (currentVibe == PersonalityVibe.KAWAII_OVERLOAD) 
            "Hari Menuju Dompet Kosong 😭" else "FINANCIAL DEATH CLOCK"
            
        Text(
            text = title,
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            modifier = Modifier.alpha(if (daysLeftToBroke <= 5) alphaAnim else 1f)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Timer Digital (Monospace)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TimeBlock(value = daysLeftToBroke.toString().padStart(2, '0'), label = "DAY")
            Text(":", fontSize = 32.sp, color = colors.primary, fontFamily = FontFamily.Monospace)
            TimeBlock(value = hoursLeft.toString().padStart(2, '0'), label = "HRS")
            Text(":", fontSize = 32.sp, color = colors.primary, fontFamily = FontFamily.Monospace)
            TimeBlock(value = minsLeft.toString().padStart(2, '0'), label = "MIN")
        }
    }
}

@Composable
fun TimeBlock(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 36.sp,
            color = Color.White,
            fontFamily = FontFamily.Monospace, // Wajib Monospace agar ala bomb digital
            fontWeight = FontWeight.Black
        )
        Text(text = label, fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
    }
}
