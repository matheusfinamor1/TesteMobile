package com.example.testemobile.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val emailComprador: String,
    val timestampCadastro: Long,
    val modeloId: Int,
    val ano: Int,
    val combustivel: String,
    val numPortas: Int,
    val cor: String,
    val nomeModelo: String,
    val valor: Double
)