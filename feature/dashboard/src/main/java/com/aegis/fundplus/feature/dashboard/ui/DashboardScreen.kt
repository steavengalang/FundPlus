package com.aegis.fundplus.feature.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aegis.fundplus.feature.dashboard.DashboardUiState
import com.aegis.fundplus.feature.dashboard.DashboardViewModel
import com.aegis.fundplus.feature.dashboard.ui.components.ButterflyEffectCard
import com.aegis.fundplus.feature.dashboard.ui.components.FluidLineChart
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Evaluasi AI Mood Shift (Cahaya Redup jika Saldo Drop)
    val isBudgetCritical = uiState.totalBalance < (uiState.monthlyExpense * 1.5) // Contoh logika savage
    val backgroundColor = if (isBudgetCritical) Color(0xFFFAF9F9) else Color(0xFFFFFFFF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Mood Shift UI Engine
            .verticalScroll(scrollState)
            .padding(top = 48.dp, bottom = 24.dp)
    ) {
        // 1. Label Saldo Ala CEO
        Text(
            text = "Total Saldo Bersih",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Text(
            text = formatCurrency(uiState.totalBalance),
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            letterSpacing = (-1).sp,
            modifier = Modifier.padding(horizontal = 24.dp, top = 4.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Fluid Minimalist Chart (The Apple-Style Canvas)
        FluidLineChart(
            dataPoints = uiState.chartDataPoints,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp) // Edge to edge elegan
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 3. AI Insight Card
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            ButterflyEffectCard(aiInsightMessage = uiState.aiInsight)

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Monthly Split (Income vs Expense)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                FinanceMetric(title = "Pemasukan", amount = uiState.monthlyIncome, color = Color(0xFF4CAF50))
                FinanceMetric(title = "Pengeluaran", amount = uiState.monthlyExpense, color = Color(0xFFE53935))
            }
        }
    }
}

@Composable
fun FinanceMetric(title: String, amount: Double, color: Color) {
    Column {
        Text(text = title, fontSize = 13.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
        Text(
            text = formatCurrency(amount),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = color,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

fun formatCurrency(amount: Double): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(amount).replace(",00", "") 
}
