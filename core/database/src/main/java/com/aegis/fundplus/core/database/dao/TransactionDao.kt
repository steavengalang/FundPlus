package com.aegis.fundplus.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aegis.fundplus.core.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY transactionDate DESC LIMIT 50")
    fun getRecentTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'EXPENSE' AND transactionDate BETWEEN :startDate AND :endDate")
    fun getTotalExpenseInPeriod(startDate: Long, endDate: Long): Flow<Double?>
    
    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'INCOME' AND transactionDate BETWEEN :startDate AND :endDate")
    fun getTotalIncomeInPeriod(startDate: Long, endDate: Long): Flow<Double?>
}
