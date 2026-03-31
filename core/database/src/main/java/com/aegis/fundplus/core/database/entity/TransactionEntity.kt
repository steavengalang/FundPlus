package com.aegis.fundplus.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(entity = AccountEntity::class, parentColumns = ["accountId"], childColumns = ["accountId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["categoryId"], childColumns = ["categoryId"], onDelete = ForeignKey.RESTRICT)
    ],
    indices = [Index("accountId"), Index("categoryId")]
)
data class TransactionEntity(
    @PrimaryKey val transactionId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "accountId") val accountId: String,
    @ColumnInfo(name = "categoryId") val categoryId: String,
    val amount: Double,
    val type: String, // INCOME, EXPENSE, TRANSFER
    val toAccountId: String? = null, // Untuk transfer
    val transactionDate: Long, // Epoch millis
    val note: String,
    val receiptImagePath: String? = null
)
