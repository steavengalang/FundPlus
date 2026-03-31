package com.aegis.fundplus.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val categoryId: String = UUID.randomUUID().toString(),
    val name: String,
    val type: String, // INCOME, EXPENSE, TRANSFER
    val iconRes: String,
    val colorHex: String,
    val budgetLimit: Double? = null // Budget opsional bulanan
)
