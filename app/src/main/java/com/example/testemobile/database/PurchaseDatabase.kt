package com.example.testemobile.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PurchaseEntity::class], version = 1, exportSchema = true)
abstract class PurchaseDatabase : RoomDatabase(){
    abstract fun purchaseDao(): PurchaseDao
}