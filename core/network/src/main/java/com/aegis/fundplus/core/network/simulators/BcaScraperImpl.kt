package com.aegis.fundplus.core.network.simulators

import com.aegis.fundplus.core.network.BankIntegrationEngine
import com.aegis.fundplus.core.network.BankTransactionDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Simulator mesin pengorek data (Scraper) untuk BCA.
 * Pada implementasi nyata, kelas ini menggunakan JSoup HTML Parser atau API Retrofit.
 */
class BcaScraperImpl @Inject constructor() : BankIntegrationEngine {
    
    override val bankName: String = "BCA"

    override suspend fun loginAndGetToken(encryptedCreds: ByteArray): String {
        // Mocking proses dekripsi dan login ke e-Banking
        delay(1500) // Simulasi loading server
        return "BCA_MOCK_TOKEN_#8921"
    }

    override suspend fun fetchRecentTransactions(token: String): Flow<List<BankTransactionDto>> = flow {
        delay(1000)
        // Data simulasi penarikan dari mutasi
        val mockData = listOf(
            BankTransactionDto("BCA-001", 50000.0, "MCDONALDS SUDIRMAN", System.currentTimeMillis() - 86400000, "EXPENSE"),
            BankTransactionDto("BCA-002", 1500000.0, "TRANSFER DARI BPK BUDI", System.currentTimeMillis(), "INCOME")
        )
        emit(mockData)
    }

    override suspend fun fetchCurrentBalance(token: String): Double {
        delay(500)
        return 28500000.0 // Simulasi saldo aktual di bank BCA
    }
}
