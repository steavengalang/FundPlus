package com.aegis.fundplus.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey val accountId: String = UUID.randomUUID().toString(),
    val name: String,
    val type: String, // CASH, BANK, E_WALLET
    val balance: Double, // Double to prevent integer overflow
    val iconRes: String,
    val colorHex: String,
    val isIntegrated: Boolean,
    val createdAt: Long = System.currentTimeMillis()
)
