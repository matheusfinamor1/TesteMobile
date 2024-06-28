package com.example.testemobile.repository.purchase

import com.example.testemobile.database.PurchaseEntity
import com.example.testemobile.response.InsertPurchaseResult
import com.example.testemobile.response.Resource
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun insertPurchase(purchaseEntity: PurchaseEntity)
    suspend fun getPurchase(): Flow<PurchaseEntity>

}