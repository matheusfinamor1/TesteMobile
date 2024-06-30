package com.example.testemobile.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseEntity)

    @Query("SELECT * FROM purchase ORDER BY id ASC")
    fun getPurchase(): Flow<PurchaseEntity>

    @Delete
    suspend fun deletePurchase(purchase: PurchaseEntity)
}