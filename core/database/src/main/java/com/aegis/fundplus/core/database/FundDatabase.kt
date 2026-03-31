package com.aegis.fundplus.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aegis.fundplus.core.database.dao.TransactionDao
import com.aegis.fundplus.core.database.entity.AccountEntity
import com.aegis.fundplus.core.database.entity.CategoryEntity
import com.aegis.fundplus.core.database.entity.TransactionEntity

@Database(
    entities = [AccountEntity::class, CategoryEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FundDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
