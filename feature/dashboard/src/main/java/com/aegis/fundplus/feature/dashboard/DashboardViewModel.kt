package com.aegis.fundplus.feature.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class DashboardUiState(
    val totalBalance: Double = 0.0,
    val monthlyIncome: Double = 0.0,
    val monthlyExpense: Double = 0.0,
    val chartDataPoints: List<Float> = emptyList(),
    val aiInsight: String = "Lumi sedang menganalisis pola keuanganmu..."
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    // private val transactionDao: TransactionDao (Di-inject dari lapis core:database)
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        // Simulasi Kueri Reaktif ke Room Database (Tingkat 0-1)
        loadSimulatedData()
    }

    private fun loadSimulatedData() {
        // Di fase sebenarnya, ini dipompa dari "transactionDao.getRecentTransactions().collect { ... }"
        _uiState.update { 
            it.copy(
                totalBalance = 45200000.0,
                monthlyIncome = 15000000.0,
                monthlyExpense = 7800000.0,
                chartDataPoints = listOf(10f, 15f, 12f, 25f, 22f, 30f, 28f, 40f, 45f), // Trend stabil naik
                aiInsight = "Jika kamu stop pesen Kopi Susu per hari ini, kamu bisa membeli Mac Mini impianmu dalam 8 Bulan, 10 Hari."
            )
        }
    }
}
