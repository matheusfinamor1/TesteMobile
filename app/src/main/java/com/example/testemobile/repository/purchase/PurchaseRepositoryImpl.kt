package com.example.testemobile.repository.purchase

import com.example.testemobile.database.PurchaseDao
import com.example.testemobile.database.PurchaseEntity
import kotlinx.coroutines.flow.Flow

class PurchaseRepositoryImpl(
    private val purchaseDao: PurchaseDao,
) : PurchaseRepository {

    override suspend fun insertPurchase(purchaseEntity: PurchaseEntity) =
        purchaseDao.insertPurchase(purchaseEntity)

    override suspend fun getPurchase(): Flow<PurchaseEntity> = purchaseDao.getPurchase()

    override suspend fun deletePurchase(purchaseEntity: PurchaseEntity) {
        purchaseDao.deletePurchase(purchaseEntity)
    }

}