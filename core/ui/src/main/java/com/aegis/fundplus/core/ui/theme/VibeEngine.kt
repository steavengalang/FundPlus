package com.aegis.fundplus.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Tema Enum Mutiversitas: Mengatur seluruh DNA aplikasi
enum class PersonalityVibe {
    KAWAII_OVERLOAD, // Pastel, Imut, Memori RAM kecil
    NEO_TOKYO,       // Gelap, Neon, Hacker Typography
    BILLIONAIRE_SLATE // Elegan, Tipis, Monokrom, Profesional Biasa
}

// Pusat Desain (Theming Factory)
object VibeEngine {

    fun getColors(vibe: PersonalityVibe): AppColorScheme {
        return when (vibe) {
            PersonalityVibe.KAWAII_OVERLOAD -> KawaiiColors()
            PersonalityVibe.NEO_TOKYO -> CyberpunkColors()
            PersonalityVibe.BILLIONAIRE_SLATE -> ExecutiveMonochromeColors()
        }
    }

    fun getTypography(vibe: PersonalityVibe): Typography {
        return when (vibe) {
            PersonalityVibe.KAWAII_OVERLOAD -> KawaiiTypo()
            PersonalityVibe.NEO_TOKYO -> HackerTypo()
            PersonalityVibe.BILLIONAIRE_SLATE -> SerifTypo()
        }
    }
    
    fun getAIVoiceTone(vibe: PersonalityVibe): String {
        return when(vibe) {
            PersonalityVibe.KAWAII_OVERLOAD -> "Wah, senpai hebat banget bulan ini nabungnya! (✿◠‿◠)"
            PersonalityVibe.NEO_TOKYO -> "FUNDS SECURED. TARGET ACQUIRED."
            PersonalityVibe.BILLIONAIRE_SLATE -> "Laporan pengeluaran bulan ini sudah siap di meja Anda, Tuan."
        }
    }
}

// --- Dummies for Interface ---

interface AppColorScheme {
    val background: androidx.compose.ui.graphics.Color
    val surface: androidx.compose.ui.graphics.Color
    val primary: androidx.compose.ui.graphics.Color
    val textMain: androidx.compose.ui.graphics.Color
    val accentThreat: androidx.compose.ui.graphics.Color // Warna saat pengeluaran jebol
}

class KawaiiColors : AppColorScheme {
    override val background = androidx.compose.ui.graphics.Color(0xFFFFF0F5)
    override val surface = androidx.compose.ui.graphics.Color(0xFFFFE4E1)
    override val primary = androidx.compose.ui.graphics.Color(0xFFFF69B4)
    override val textMain = androidx.compose.ui.graphics.Color(0xFF8B4513)
    override val accentThreat = androidx.compose.ui.graphics.Color(0xFFFF7F50)
}

class CyberpunkColors : AppColorScheme {
    override val background = androidx.compose.ui.graphics.Color(0xFF0F0F0F)
    override val surface = androidx.compose.ui.graphics.Color(0xFF1E1E1E)
    override val primary = androidx.compose.ui.graphics.Color(0xFF39FF14) // Neon Green
    override val textMain = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
    override val accentThreat = androidx.compose.ui.graphics.Color(0xFFFF00FF) // Magenta Glitch
}

class ExecutiveMonochromeColors : AppColorScheme {
    override val background = androidx.compose.ui.graphics.Color(0xFFF9F9F9)
    override val surface = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
    override val primary = androidx.compose.ui.graphics.Color(0xFF222222)
    override val textMain = androidx.compose.ui.graphics.Color(0xFF111111)
    override val accentThreat = androidx.compose.ui.graphics.Color(0xFF8B0000) // Deep Red
}

// Simulasi Tipografi
fun KawaiiTypo() = Typography(bodyLarge = TextStyle(fontFamily = FontFamily.Cursive, fontSize = 18.sp))
fun HackerTypo() = Typography(bodyLarge = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold))
fun SerifTypo() = Typography(bodyLarge = TextStyle(fontFamily = FontFamily.Serif, letterSpacing = 0.5.sp))
