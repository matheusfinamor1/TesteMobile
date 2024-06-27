package com.example.testemobile.repository.purchase

import com.example.testemobile.database.PurchaseEntity

interface PurchaseRepository {
    suspend fun insertPurchase(purchaseEntity: PurchaseEntity)
}