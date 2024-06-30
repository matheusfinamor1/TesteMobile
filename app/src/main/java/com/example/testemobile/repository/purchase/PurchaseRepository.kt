package com.example.testemobile.repository.purchase

import com.example.testemobile.database.PurchaseEntity
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun insertPurchase(purchaseEntity: PurchaseEntity)
    suspend fun getPurchase(): Flow<PurchaseEntity>
    suspend fun deletePurchase(purchaseEntity: PurchaseEntity)
}