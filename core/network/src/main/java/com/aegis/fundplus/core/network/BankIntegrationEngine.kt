package com.aegis.fundplus.core.network

import kotlinx.coroutines.flow.Flow

// Model Data Ringan
data class BankTransactionDto(
    val transactionId: String,
    val amount: Double,
    val note: String,
    val timestamp: Long,
    val type: String // INCOME / EXPENSE
)

// Abstact Factory Layer
interface BankIntegrationEngine {
    val bankName: String
    suspend fun loginAndGetToken(encryptedCreds: ByteArray): String
    suspend fun fetchRecentTransactions(token: String): Flow<List<BankTransactionDto>>
    suspend fun fetchCurrentBalance(token: String): Double
}
