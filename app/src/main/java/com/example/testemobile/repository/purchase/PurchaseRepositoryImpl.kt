package com.example.testemobile.repository.purchase

import com.example.testemobile.database.PurchaseDao
import com.example.testemobile.database.PurchaseEntity

class PurchaseRepositoryImpl(
    private val purchaseDao: PurchaseDao
): PurchaseRepository {
    override suspend fun insertPurchase(purchaseEntity: PurchaseEntity) =
        purchaseDao.insertPurchase(purchaseEntity)
}